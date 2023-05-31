package com.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController  {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")

    public PersonV2 getBothNames(){
        return new PersonV2(new Name("Bob","Charlie"));
    }





    @GetMapping(path = "/person/", params = "version=1")
    public PersonV1 getFirstVersionOfPersonParam(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/", params = "version=2")
    public PersonV2 getBothNamesParam(){
        return new PersonV2(new Name("Bob","Charlie"));
    }




    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonHeader(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getBothNamesHeader(){
        return new PersonV2(new Name("Bob","Charlie"));
    }



    @GetMapping(path = "/person/accept", produces=  "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonProduce(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getBothNamesHeaderProduce(){
        return new PersonV2(new Name("Bob","Charlie"));
    }
}
