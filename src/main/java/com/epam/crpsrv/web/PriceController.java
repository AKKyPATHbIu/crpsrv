package com.epam.crpsrv.web;

import com.epam.crpsrv.exception.CrpSrvException;
import com.epam.crpsrv.service.CryptoPriceService;
import java.io.IOException;
import java.util.List;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Validated
@RestController
@RequestMapping("price")
public class PriceController {

    @Autowired
    private CryptoPriceService cryptoPriceService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {"application/json"})
    void upload(@RequestPart(value = "files") @Size(max = 5) List<MultipartFile> files) {
        files.forEach(f -> {
            try {
                cryptoPriceService.saveFromByteContent(f.getBytes());
            } catch (IOException ex) {
                throw new CrpSrvException("Failed to import file");
            }
        });
    }
}