package com.gcatechnologies.services.implementations;

import com.gcatechnologies.dto.MethodPaymentDto;
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
            System.out.println("EMPTY");
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
        /*Optional<MethodPaymentDto> methodPaymentDtoOptional = getById(methodPaymentDto.getId());
        if(methodPaymentDtoOptional.isPresent()) {
            return methodPaymentDto;
        }*/

        if(methodPaymentDto.getNumberCard() == null) {
            throw new RuntimeException("Pora favor ingrese el numero de la tarjeta");
        }

        if(methodPaymentDto.getTypePayment().equals(CONST_TYPE_PAYMENT_CASH)) {
            methodPaymentDto.setNumberCard(0);
        }

        return iMethodPaymentRepository.save(methodPaymentDto);
    }

    @Override
    public MethodPaymentDto saveNewMethodPaymentByUser(MethodPaymentDto methodPaymentDto) {
        return null;
    }

    @Override
    public Optional<MethodPaymentDto> updateByUsers(MethodPaymentDto methodPaymentDto) {
        List<MethodPaymentDto> methodPaymentDtoList = getByUserId(methodPaymentDto.getUsersId());

        if(methodPaymentDtoList.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if(methodPaymentDto.getTypePayment().equals(CONST_TYPE_PAYMENT_CASH)) {
            methodPaymentDto.setNumberCard(0);
        }

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
     * ESTAMOS ELIMINANDO 1 USUARIO DE UN METODO DE PAGO
     * @param userId
     * @return
     */
    @Override
    public Boolean deleteByUser(Long methodPaymentId, Long userId) {
        List<MethodPaymentDto> methodPaymentDtoList = getByUserId(userId);
        if(methodPaymentDtoList.isEmpty()) {
            return false;
        }
        int i = 0;
        for(MethodPaymentDto methodPayment: methodPaymentDtoList) {
            System.out.println("La i -> " +i);
            if(methodPayment.getId() == methodPaymentId) {
                iMethodPaymentRepository.deleteByUserId(methodPaymentId);
                break;
            }
            i++;
        }
        return true;
    }
}