package com.itclass.controller;

import com.itclass.domain.Good;
import com.itclass.repository.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodController {
    private final GoodRepository goodRepository;

    @GetMapping
    public RestResponse goods() {
        return RestResponse.builder()
                .isSuccess(true)
                .response(StreamSupport.stream(goodRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList()))
                .build();
    }

    @GetMapping("/{id}")
    public RestResponse findGoodByIid(@PathVariable Long id) {
        return RestResponse.builder()
                .isSuccess(true)
                .response(goodRepository.findById(id).orElse(null))
                .build();
    }

    @PostMapping("/order")
    public RestResponse createOrder(@RequestBody List<Good> goods) {
        return RestResponse.builder().isSuccess(true).response(goods).build();
    }

    @PostMapping
    public ResponseEntity<?> goods1(@RequestBody Good good) {
        Good savedGood = goodRepository.save(good);
        return ResponseEntity.created(URI.create("/goods/"+savedGood.getId())).body("Created");
    }

    @PutMapping("/{id}")
    public RestResponse good1s(@PathVariable Long id, @RequestBody Good good) {
        if (goodRepository.existsById(id)) {
            if (!good.getId().equals(id)) {
                return RestResponse.builder().isSuccess(false).response("SOrry bratan, no id ne sovpadaut").build();
            } else {
                goodRepository.save(good);
                return RestResponse.builder().isSuccess(true).response("Vse ook bratan").build();
            }
        } else {
            return RestResponse.builder().isSuccess(false).response("SOrry bratan, no takogo tovara net@!").build();
        }

    }

    @DeleteMapping("/{id}")
    public RestResponse goo2ds(@PathVariable Long id) {
        if (goodRepository.existsById(id)) {
            goodRepository.deleteById(id);
            return RestResponse.builder().isSuccess(true).response("Vse ook bratan,ya udalil").build();
        } else {

            return RestResponse.builder().isSuccess(false).response("SOrry bratan, no tovara s takim id net").build();
        }
    }


}
