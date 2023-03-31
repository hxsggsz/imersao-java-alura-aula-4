package com.ranking.languages.rankinglanguages;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LanguageController {
  @Autowired
  private LanguageRepository repo;

  @GetMapping("/")
  public List<Language> getLanguage() {
    return repo.findAll();
  }

  @PostMapping("/")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Language AddLanguage(@RequestBody Language language) {
    return repo.save(language);
  }

  @PutMapping("/update/{id}")
  public Language ChangeLanguage(@PathVariable String id, @RequestBody Language language) {
    if(!repo.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    language.setId(id);
    return repo.save(language);
  }

  @DeleteMapping("/delete/{id}")
  public void DeleteLang(@PathVariable String id) {
    repo.deleteById(id);
  }
}

