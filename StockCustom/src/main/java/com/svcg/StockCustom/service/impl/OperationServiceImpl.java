package com.svcg.StockCustom.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.entity.Article;
import com.svcg.StockCustom.entity.Operation;
import com.svcg.StockCustom.entity.OperationDetail;
import com.svcg.StockCustom.enums.OperationStatus;
import com.svcg.StockCustom.enums.OperationType;
import com.svcg.StockCustom.enums.PaymentMethod;
import com.svcg.StockCustom.repository.ArticleRepository;
import com.svcg.StockCustom.repository.OperationDetailRepository;
import com.svcg.StockCustom.repository.OperationRepository;
import com.svcg.StockCustom.service.OperationService;
import com.svcg.StockCustom.service.converter.OperationConverter;
import com.svcg.StockCustom.service.converter.OperationDetailConverter;
import com.svcg.StockCustom.service.dto.OperationDTO;
import com.svcg.StockCustom.service.dto.OperationDetailDTO;

@Service("operationServiceImpl")
public class OperationServiceImpl implements OperationService {

	@Autowired
	@Qualifier("operationRepository")
	private OperationRepository operationRepository;

	@Autowired
	@Qualifier("operationDetailRepository")
	private OperationDetailRepository operationDetailRepository;

	@Autowired
	@Qualifier("articleRepository")
	private ArticleRepository articleRepository;

	@Autowired
	Messages messages;

	@Autowired
	private OperationConverter operationConverter;
	
	@Autowired
    private OperationDetailConverter operationDetailConverter;

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Override
	@Transactional
	public OperationDTO saveOperation(OperationDTO operationDTO) {
		//como manejo el estado por ahora viene desde el front, el total , el subtotal ??
		operationDTO.setCreateDate(new Date());
		operationDTO.setCreateDateTime(new Date());
		OperationDTO newOperationDTO = this.operationConverter.toDTO(this.operationRepository.save(this.operationConverter.toEntity(operationDTO)));
		logger.info("Operacion guardada con exito: {}", newOperationDTO.toString());
		
		if (newOperationDTO != null) {
			List<OperationDetailDTO> operationDetailsDTO = operationDTO
					.getOperationDetails();

			// que sucede en el caso que se estan registrando despertidisios ??
			// aplica ??
			if (!operationDetailsDTO.isEmpty()) {
				this.updateArticles(this.operationDetailConverter.toEntity(operationDetailsDTO), this.operationConverter.toEntity(newOperationDTO));
			}

		}
		return newOperationDTO;

	}

    @Override
    @Transactional
    public OperationDTO updateOperation(OperationDTO operationDTO) {
	    Operation oldOperation = this.operationRepository.findByOperationId(operationDTO.getOperationId());
        Operation newOperation = new Operation();
        newOperation.setCreateDate(new Date());
        newOperation.setCreateDateTime(new Date());
        newOperation.setOperationStatus(operationDTO.getOperationStatus());
        newOperation.setDisabled(operationDTO.getDisabled());
        newOperation.setOperationDetails(this.operationDetailConverter.toEntity(operationDTO.getOperationDetails()));
        newOperation.setOperationType(operationDTO.getOperationType());
        newOperation.setPaymentMethod(operationDTO.getPaymentMethod());
        newOperation.setSubTotal(operationDTO.getSubTotal());
        newOperation.setTotal(operationDTO.getTotal());

        List<OperationDetail> operationDetails = newOperation
                .getOperationDetails();
        oldOperation.getOperationDetails().forEach(opd -> {
            Article article = articleRepository
                    .findByArticleId(opd.getArticleId());
            if (opd.getArticleId().equals(opd.getArticleId())) {
                article.setCurrentQuantity(article.getCurrentQuantity() + opd.getAmount());
                articleRepository.save(article);
                logger.info("Stock de Articulo restaurado con exito: {}", article);
            }
        });
        newOperation = this.operationRepository.save(newOperation);
        this.updateArticles(operationDetails, newOperation);
        this.disabledOperation(oldOperation);
        this.operationRepository.save(oldOperation);
        return this.operationConverter.toDTO(newOperation);
    }

	@Override
	public Page<OperationDTO> getOperationsByCreationDate(Date createDate, Pageable pageable) {
		Page<Operation> operations = this.operationRepository.findByCreateDate(createDate, pageable);
		if (operations == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.map(this.operationConverter::toDTO);
	}

    @Override
	public OperationDTO getOperationById(Long id) {
		Operation operation = this.operationRepository
				.findByOperationId(id);
		if (operation == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}
        List<OperationDetail> detailOperationList = operationDetailRepository.findByOperationId(id);
        operation.setOperationDetails(detailOperationList);

		return this.operationConverter.toDTO(operation);
	}

	@Override
	public List<OperationType> getOperationTypes() {
		return  Arrays.asList(OperationType.values());
	}

	@Override
	public List<PaymentMethod> getPaymentMethods() {
		 return  Arrays.asList(PaymentMethod.values());
	}

    @Override
    public OperationDTO cancelOperation(Long id) {
        Operation operation = this.operationRepository.findByOperationId(id);
        this.disabledOperation(operation);
        this.updateArticles(operation.getOperationDetails(), operation);
        return this.operationConverter.toDTO(this.operationRepository.save(operation));
    }

    private void disabledOperation(Operation operation) {
        operation.setDisabled(true);
        operation.setDisabledDate(new Date());
        operation.setOperationStatus(OperationStatus.CANCELED);
    }

    private void updateArticles(List<OperationDetail> operationDetails, Operation newOperation) {
        logger.info("Cantidad de detalles de operacion es {}  para la operation con id {}", operationDetails.size(), newOperation.getOperationId());
        for (OperationDetail detailOperation : operationDetails) {
            detailOperation.setOperationId(newOperation
                    .getOperationId());
            operationDetailRepository.save(detailOperation);

            // actualizo el stock del producto
            Article article = articleRepository
                    .findByArticleId(detailOperation.getArticleId());
            //TODO: solo si la categoria no es carne(categoria carne es la con id 1) se debe sacar cuando este la funcionalidad de la balanza electronica 
            if(article.getCategoryId() != 1) {
            	Double newQuantityArticle = (newOperation.isDisabled())? (article.getCurrentQuantity() + detailOperation.getAmount())  : (article.getCurrentQuantity() - detailOperation.getAmount());
            	article.setCurrentQuantity(newQuantityArticle);
            	articleRepository.save(article);
            	logger.info("Stock de Articulo actualizado con exito: {}", article.toString());
            }
        }
    }

	@Override
	public Page<OperationDTO> getOperationsByCreationDateAndOperationType(Date createDate, Pageable pageable) {
		return null;
	}

}
