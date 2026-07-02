package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Mail;



public class MailServiceTarea implements Runnable{

    //@Inject
    private MailService mailService;

    private Mail mail;

    public MailServiceTarea(Mail mail, MailService mailService){
        this.mail = mail;
        this.mailService = mailService;
    }

    @Override
    public void run(){

        this.mailService.guardar(this.mail);
    }
}
