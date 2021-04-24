package ninja.i3s.example.errorlocalization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ninja.i3s.example.errorlocalization.bean.ErrorlocalizationValidationError;
import ninja.i3s.example.errorlocalization.configuration.CodeListSettings;
import ninja.i3s.example.errorlocalization.logic.ErrorlocalizationValidation;

@RestController
public class ErrorlocalizationController {

  @Autowired
  private CodeListSettings codeListSettings;

  @PostMapping("/api/validate")
  List<ErrorlocalizationValidationError> all(@RequestParam(value = "country") String country,
      @RequestParam(value = "weather") String weather) {
    
    // validation occurs
    return ErrorlocalizationValidation.validate(country, weather, codeListSettings);
  }
}