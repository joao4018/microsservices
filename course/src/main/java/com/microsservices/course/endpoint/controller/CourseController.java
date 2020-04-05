package com.microsservices.course.endpoint.controller;


import com.microsservices.core.model.ApplicationUser;
import com.microsservices.core.model.Course;
import com.microsservices.course.endpoint.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * @author joao4018
 */
@RestController
@RequestMapping("v1/admin/course")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(value = "Endpoints to manage course")
public class CourseController {
    private static String bla = "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiZGlyIn0..nHzzG0Rebb0an2g2BILwhg.CHjtcAPyw3lcToybbLCtXpwrZax1Wn8bUp_kxP4jfz-E0nY9HZNsQjgfy74tmJFdpE2EdaHxhvLKnq29m_UnfA0kWUJhtu41n7chBQYpF6sq3DMVQv5Y-fxG8hfl7l2NjeRKx4cY1D-0i8wjFQnzsAKeHeIIIu3NwoOkiBhHGtGbtpq6K9mLpd96tw5lJ0132rjB7QO6QSpV1K3RT3NmAdTNMSE4RXjCVAspC6eNu0gwmsRyWoT7UQYjVnx8ZBtG4fMU8jRxFUm735eUh2YGKVD693-u5ltGBWZT9rAr4pwGdM_jC0V2gODG05BfhUuFo37wgWuwm46HUwUFxW-LSd5O9M90erdnVzp0PRs9nDYZgvyonL6iI0EHPWF-zrJ2xCmiUVXkEhaX-xVxjZBc411hQlZ0kCz345CBfY8VtCVno5M6LyqnE_arSj6Im6tH_fHK1VQZE4Pus0mjU36EhJnfbNFHWfA7ZEbA4slcZjkCnTnqIYp2vNQCQlVXZSIJEAzXN2_4nB6ZGt9CasM7wsAW0sqU1ehC_W7opBs5nG5CHbiq-90wFpa1no79PiBVO1RRhxn9b809QOy9Hn_QA04SCe96JYO9fe8CtDocihK-MKmibV5knWIn8VlxHTPBIym5oDMV2HmzVp3ph1ftaeykZKeQkU_KYAc2MXSMcxPas3701H0RjbvNSdOU8TJn1VtWs-vpKQemE-kTwkXMvNCS9OR367nINu22uEZwwpIi6kL4Vut2Kg_1xlMir5GoCUIMzPZABL777qbqv7VNe3kG_Lrq5wb3UpB61oxZDTjJljEsR5JYQG5DM97SPu44G0HG67MKUGDFurDZq2KYT5vpXY3SBZNCxH8d0aHJN0f44_RIrkJ0rcWJ08KBaO-QWXenBnt5zjnPqEqjoiR5fn83cjosxqX83CWK04Aba6NjMHvcZ9xgXosuaaLrYLyOtC9kzT1YrZqX99NQuMj47vADl5pmnDpqapXV9oUEJHHo81jxGoV-aQCqX_ZnLeZBsziz1YNapwOCAiq2zYRXSY_zbrlBranBeddoNUhm76LT2IFE_bNS_ZN-RJgJQnn9fAHkCZqvqM7E6uMNsyMZ2YiTanckdySeA-iJjxJAg5RIYJbhJ87PIpo_A_m68XlUOdVAHhe9U0SsKNC8ofaNCtSmy3VlRzkl2vdM1-YYbvU9g2sA8canecJP0Z4DR6DIYDfbvaRLRfHETI18JI1kOPt6_mH0MNThXDvvk1oRPaSCk_s8OTBvZjZ1f21ct8x_ib4UlwI54QS1-Aj6dZiGdNWeFMsrrq8egH11RafZgmpNhixQsCnXjQEy0-FZnSeCkMNTseCelVB8VNkLc7omogzo9xkWg73shze4cimuCqmlLZ22QfCMwBkmGm5jf1q5sNgLFRzVWvlKOdIbu6_hqLavhqtMpXWT7gM7OHQfztuifZtes3jGUeUCEEzwoicOxq_GXSNNehgun-6l5cNTIA.lOB4a3ZKergvc2Nn9MJFKw";
    private final CourseService courseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "List all available courses", response = Course[].class)
    public ResponseEntity<Iterable<Course>> list(Pageable pageable) {
        return new ResponseEntity<>(courseService.list(pageable), HttpStatus.OK);
    }

    @PostMapping(path = "/students")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Course student) {
        RestTemplate template = new RestTemplate();
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost:8080")
                .path("gateway/auth/user/info")
                .build();
        HttpEntity<String> entity = new HttpEntity<String>("parameters", createJSONHeader());
        ResponseEntity<ApplicationUser> response = template.exchange(uri.toUriString(), HttpMethod.GET, entity, ApplicationUser.class);
        student.setFk(response.getBody().getId());
        return new ResponseEntity<>(courseService.save(student),HttpStatus.CREATED);
    }

    private static HttpHeaders createJSONHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+bla);
//        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
//        String result = restTemplate.postForObject(url, entity, String.class);
        return headers;
    }


}
