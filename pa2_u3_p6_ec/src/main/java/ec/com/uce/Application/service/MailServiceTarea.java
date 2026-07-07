package ec.com.uce.Application.service;

import ec.com.uce.Domain.model.Mail;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;


@Dependent
public class MailServiceTarea implements Runnable{

    @Inject
    private MailService mailService;

    private Mail mail;

    @Override
    public void run(){

        this.mailService.guardar(this.mail);
    }
}
