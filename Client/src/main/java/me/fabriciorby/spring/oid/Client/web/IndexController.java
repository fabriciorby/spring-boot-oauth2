package me.fabriciorby.spring.oid.Client.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getHome(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                          @AuthenticationPrincipal OAuth2User oauth2User, Model model) {
        model.addAttribute("userInfo", oauth2User.getAttributes());
        model.addAttribute("accessToken", "Bearer " + authorizedClient.getAccessToken().getTokenValue());
        model.addAttribute("scopes", authorizedClient.getAccessToken().getScopes());
        return "index";
    }

    @GetMapping("/loginSuccess")
    public String getLoginInfo(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                               @AuthenticationPrincipal OAuth2User oauth2User, Model model) {
        return getHome(authorizedClient, oauth2User, model);
    }

    @GetMapping("/loginFailure")
    public String getLoginFailure(Model model) {
        return "loginFailure";
    }
}
