package de.ait.javalessons.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/")
    public String homepage() {
        return "Главная страница";
    }

    @GetMapping("/public/info")
    public String publishInfoPage() {
        return "Страница доступна без авторизации";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "Личный кабинет пользователя";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Админский раздел";
    }
}
