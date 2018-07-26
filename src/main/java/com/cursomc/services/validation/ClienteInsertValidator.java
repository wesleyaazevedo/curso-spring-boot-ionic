package com.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cursomc.domain.Cliente;
import com.cursomc.domain.enums.TipoCliente;
import com.cursomc.dto.ClienteNewDTO;
import com.cursomc.repositories.ClienteRepository;
import com.cursomc.resources.exception.FieldMessage;
import com.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		if (objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDTO.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CPF inválido."));
		}
		
		if (objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDTO.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "CNPJ inválido."));
		}
		
		Cliente aux = repo.findByEmail(objDTO.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email","O email já está cadastrado."));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
				   .addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}