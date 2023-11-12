package com.gcatechnologies.services.implementations;

import com.gcatechnologies.dto.MethodPaymentDto;
import com.gcatechnologies.exceptions.NumberCardExistException;
import com.gcatechnologies.exceptions.ValidatedNumberCard;
import com.gcatechnologies.repositories.contracts.IMethodPaymentRepository;
import com.gcatechnologies.services.contracts.IMethodPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MethodPaymentServiceImpl implements IMethodPaymentService {

    private final IMethodPaymentRepository iMethodPaymentRepository;

    private final static String CONST_TYPE_PAYMENT_CASH = "Efectivo";

    @Override
    public List<MethodPaymentDto> getAll() {
        return iMethodPaymentRepository.getAll();
    }

    @Override
    public Optional<MethodPaymentDto> getById(Long methodPaymentId) {
        Optional<MethodPaymentDto> methodPaymentDtoOptional = iMethodPaymentRepository.getById(methodPaymentId);
        if(methodPaymentDtoOptional.isEmpty()) {
            return Optional.empty();
        }
        return methodPaymentDtoOptional;
    }

    @Override
    public List<MethodPaymentDto> getByUserId(Long userId) {
        List<MethodPaymentDto> methodPaymentDtoList = iMethodPaymentRepository.getByUserId(userId);
        if(methodPaymentDtoList.isEmpty()) {
            return Collections.emptyList();
        }
        return methodPaymentDtoList;
    }

    @Override
    public MethodPaymentDto save(MethodPaymentDto methodPaymentDto) {

        if(methodPaymentDto.getTypePayment().equals(CONST_TYPE_PAYMENT_CASH)) methodPaymentDto.setNumberCard(0);

        /**
         * Validamos si el numero de tarjeta ya se encuentra en la base de datos
         */
        Integer numberCardRepeated = iMethodPaymentRepository.countByNumberCard(methodPaymentDto.getNumberCard());
        if(numberCardRepeated != 0) throw new NumberCardExistException();

        if(methodPaymentDto.getNumberCard() == null) throw new ValidatedNumberCard();

        return iMethodPaymentRepository.save(methodPaymentDto);
    }

    @Override
    public Optional<MethodPaymentDto> updateByUsers(MethodPaymentDto methodPaymentDto) {
        List<MethodPaymentDto> methodPaymentDtoList = getByUserId(methodPaymentDto.getUsersId());

        if(methodPaymentDtoList.isEmpty()) {
            return Optional.empty();
        }

        if(methodPaymentDto.getTypePayment().equals(CONST_TYPE_PAYMENT_CASH)) {
            methodPaymentDto.setNumberCard(0);
        }

        /**
         * Validar que el numero de tarjeta no se modifique al momento de no ingresar un valor
         * filter: si el usuario tiene dos metodos de pago,
         * se recorre y valida que sea el medio de pago ID se igual al del Dto ID
         */
        methodPaymentDtoList.stream()
                .filter(typePayment -> typePayment.getId() == methodPaymentDto.getId())
                .forEach(x -> {
                    if(methodPaymentDto.getNumberCard() == null) {
                        methodPaymentDto.setNumberCard(x.getNumberCard());
                    }
                    if(methodPaymentDto.getTypePayment() == null) {
                        methodPaymentDto.setTypePayment(x.getTypePayment());
                    }
                });

        iMethodPaymentRepository.save(methodPaymentDto);
        return Optional.of(methodPaymentDto);
    }

    /**
     * ELIMINAR UN METODO DE PAGO DE UN USUARIO
     */
    @Override
    public Boolean deleteByUser(Long methodPaymentId, Long userId) {
        List<MethodPaymentDto> methodPaymentDtoList = getByUserId(userId);
        if(methodPaymentDtoList.isEmpty()) {
            return false;
        }

        for(MethodPaymentDto methodPayment: methodPaymentDtoList) {
            if(methodPayment.getId() == methodPaymentId) {
                iMethodPaymentRepository.deleteByUserId(methodPaymentId);
                break;
            }
        }
        return true;
    }
}