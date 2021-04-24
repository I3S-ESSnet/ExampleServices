package ninja.i3s.example.errorlocalization.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ninja.i3s.example.errorlocalization.bean.ErrorlocalizationValidationError;
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