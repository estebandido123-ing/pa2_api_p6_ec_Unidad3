package ec.edu.uce.application.service;

import ec.edu.uce.domain.model.Mail;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

//implemenmta de la interfaz runnable
@Dependent
public class MailServiceTarea implements Runnable{

    @Inject
    MailService ms;

    private Mail mail;
    

    public void setMail(Mail mail){
        this.mail= mail;

    }

    //este metodo run va a ejecutar un hilo especifico
    @Override
    public void run() {

        String nombreHilo = Thread.currentThread().getName();
        System.out.println("nombre del hilo MAILSERVICETAREA:" + nombreHilo);
        this.ms.guardarMail(this.mail);
        

    }

}
