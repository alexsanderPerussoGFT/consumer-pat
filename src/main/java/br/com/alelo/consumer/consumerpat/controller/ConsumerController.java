package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.dto.request.ConsumerRequestDto;
import br.com.alelo.consumer.consumerpat.dto.response.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.service.ConsumerCreationService;
import br.com.alelo.consumer.consumerpat.service.ConsumerSearchService;
import br.com.alelo.consumer.consumerpat.service.ConsumerUpdateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerCreationService consumerCreationService;

    @Autowired
    ConsumerUpdateService consumerUpdateService;

    @Autowired
    ConsumerSearchService consumerSearchService;


    @ApiOperation(value = "Lista todos Clientes")
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public List<ConsumerResponse> listAllConsumers(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {
        return consumerSearchService.listAllConsumers(pageNumber, pageSize);
    }

    @ApiOperation(value = "Cadastra novos clientes")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createConsumer(@RequestBody ConsumerRequestDto consumerRequest) {
        consumerCreationService.createConsumer(consumerRequest);
    }

    @ApiOperation(value = "Altera dados do cliente - menos saldo de cartao")
    @PutMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateConsumer(@RequestBody ConsumerRequestDto consumerRequest) {
        consumerUpdateService.updateConsumer(consumerRequest);
    }
}
