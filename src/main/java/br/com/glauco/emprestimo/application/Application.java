package br.com.glauco.emprestimo.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "br.com.glauco.emprestimo.entity" })
@EnableJpaRepositories(basePackages = { "br.com.glauco.emprestimo.repository" })
@ComponentScan(basePackages = {"br.com.glauco.emprestimo.controller", "br.com.glauco.emprestimo.service"})
public class Application {
    public static void main(String args[]){
        SpringApplication.run(Application.class, args);
    }
}
