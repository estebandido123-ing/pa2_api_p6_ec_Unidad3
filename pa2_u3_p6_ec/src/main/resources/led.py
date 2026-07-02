import tkinter as tk
import customtkinter as ctk
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import matplotlib.pyplot as plt
import serial
import threading
import time

# =========================
# CONFIGURACIÓN UI
# =========================
ctk.set_appearance_mode("Dark")
ctk.set_default_color_theme("blue")

class AppLedSystem(ctk.CTk):
    def __init__(self):
        super().__init__()

        self.title("Sistema de Monitoreo LED & Servo")
        self.geometry("900x650")

        # =========================
        # CONEXIÓN SERIAL (COM17)
        # =========================
        try:
            self.arduino = serial.Serial('COM17', 9600, timeout=0.1)
            print("Conectado exitosamente al Arduino en COM17")
        except Exception as e:
            print(f"Advertencia: No se pudo conectar a COM17 ({e}). Modo simulación activado.")
            self.arduino = None

        # Variables
        self.luz_izq = 0
        self.luz_der = 0
        self.sensibilidad = 5
        self.angulo_servo = 90
        self.modo_remoto = 0
        self.ultimo_angulo = 90
        self.servo_moviendose = False

        # Contenedor principal
        self.contenedor = ctk.CTkFrame(self)
        self.contenedor.pack(fill="both", expand=True)

        self.mostrar_pantalla_inicio()

        # Hilo serial
        self.corriendo = True
        self.hilo_serial = threading.Thread(target=self.leer_serial, daemon=True)
        self.hilo_serial.start()

    # =========================
    # PANTALLA INICIO
    # =========================
    def limpiar_contenedor(self):
        for widget in self.contenedor.winfo_children():
            widget.destroy()

    def mostrar_pantalla_inicio(self):
        self.limpiar_contenedor()

        # ⭐ TÍTULO PRINCIPAL
        ctk.CTkLabel(
            self.contenedor,
            text="PROYECTO CARRILLO GRANDA",
            font=("Arial", 26, "bold")
        ).pack(pady=10)

        ctk.CTkLabel(
            self.contenedor,
            text="SISTEMA DE CONTROL LUMÍNICO",
            font=("Arial", 22)
        ).pack(pady=10)

        ctk.CTkLabel(
            self.contenedor,
            text="Seleccione el modo de operación",
            font=("Arial", 16)
        ).pack(pady=10)

        ctk.CTkButton(
            self.contenedor,
            text="Modo Automático 🤖",
            command=self.ir_a_automatico
        ).pack(pady=15)

        ctk.CTkButton(
            self.contenedor,
            text="Modo Manual 🎛️",
            fg_color="green",
            command=self.ir_a_manual
        ).pack(pady=15)

    # =========================
    # MODO AUTOMÁTICO
    # =========================
    def ir_a_automatico(self):
        self.enviar_comando('A')
        self.limpiar_contenedor()

        ctk.CTkLabel(self.contenedor, text="MODO AUTOMÁTICO").pack()

        self.fig, self.ax = plt.subplots()
        self.barras = self.ax.bar(['LDR Izq', 'LDR Der'], [0, 0])

        self.canvas_plt = FigureCanvasTkAgg(self.fig, self.contenedor)
        self.canvas_plt.get_tk_widget().pack()

    # =========================
    # MODO MANUAL
    # =========================
    def ir_a_manual(self):
        self.enviar_comando('M')
        self.limpiar_contenedor()

        ctk.CTkLabel(self.contenedor, text="MODO MANUAL").pack()

    # =========================
    # SERIAL
    # =========================
    def enviar_comando(self, comando):
        if self.arduino:
            try:
                self.arduino.write(f"{comando}\n".encode())
            except:
                pass

    def leer_serial(self):
        while self.corriendo:
            if self.arduino:
                try:
                    linea = self.arduino.readline().decode().strip()
                    if linea:
                        datos = linea.split(',')

                        if len(datos) == 5:
                            # Orden Arduino:
                            # LDR_IZQ (A1), LDR_DER (A0), POT (A2), SERVO (7), LED (13)
                            self.luz_izq = int(datos[0])
                            self.luz_der = int(datos[1])
                            self.sensibilidad = int(datos[2])
                            self.angulo_servo = int(datos[3])
                            self.modo_remoto = int(datos[4])

                            self.after(0, self.actualizar_interfaz)
                except:
                    pass
            time.sleep(0.05)

    def actualizar_interfaz(self):
        try:
            self.barras[0].set_height(self.luz_izq)
            self.barras[1].set_height(self.luz_der)
            self.canvas_plt.draw()
        except:
            pass

    def destroy(self):
        self.corriendo = False
        if self.arduino:
            self.arduino.close()
        super().destroy()


if __name__ == "__main__":
    app = AppLedSystem()
    app.mainloop()