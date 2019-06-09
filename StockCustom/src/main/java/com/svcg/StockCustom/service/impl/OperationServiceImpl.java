package com.svcg.StockCustom.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.svcg.StockCustom.enums.OperationStatus;
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
import com.svcg.StockCustom.entity.Article;
import com.svcg.StockCustom.entity.OperationDetail;
import com.svcg.StockCustom.enums.OperationType;
import com.svcg.StockCustom.enums.PaymentMethod;
import com.svcg.StockCustom.entity.Operation;
import com.svcg.StockCustom.repository.ArticleRepository;
import com.svcg.StockCustom.repository.OperationDetailRepository;
import com.svcg.StockCustom.repository.OperationRepository;
import com.svcg.StockCustom.service.OperationService;

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

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Override
	@Transactional
	public Operation saveOperation(Operation operation) {
		//como manejo el estado por ahora viene desde el front, el total , el subtotal ??
		operation.setCreateDate(new Date());
		operation.setCreateDateTime(new Date());
		Operation newOperation = operationRepository.save(operation);
		logger.info("Operacion guardada con exito: " + newOperation.toString());
		
		if (newOperation != null) {
			List<OperationDetail> operationDetails = operation
					.getOperationDetails();

			// que sucede en el caso que se estan registrando despertidisios ??
			// aplica ??
			if (!operationDetails.isEmpty()) {
				this.updateArticles(operationDetails, newOperation);
			}

		}
		return newOperation;

	}

    @Override
    @Transactional
    public Operation updateOperation(Operation operation) {
	    Operation oldOperation = operationRepository.findByOperationId(operation.getOperationId());
        Operation newOperation = new Operation();
        newOperation.setCreateDate(new Date());
        newOperation.setCreateDateTime(new Date());
        newOperation.setOperationStatus(operation.getOperationStatus());
        newOperation.setDisabled(operation.isDisabled());
        newOperation.setOperationDetails(operation.getOperationDetails());
        newOperation.setOperationType(operation.getOperationType());
        newOperation.setPaymentMethod(operation.getPaymentMethod());
        newOperation.setSubTotal(operation.getSubTotal());
        newOperation.setTotal(operation.getTotal());

        List<OperationDetail> operationDetails = newOperation
                .getOperationDetails();
        oldOperation.getOperationDetails().forEach(opd -> {
            Article article = articleRepository
                    .findByArticleId(opd.getArticleId());
            if (opd.getArticleId().equals(opd.getArticleId())) {
                article.setCurrentQuantity(article.getCurrentQuantity() + opd.getAmount());
                articleRepository.save(article);
                logger.info("Stock de Articulo restaurado con exito: " + article.toString());
            }
        });
        newOperation = operationRepository.save(newOperation);
        this.updateArticles(operationDetails, newOperation);
        this.disabledOperation(oldOperation);
        operationRepository.save(oldOperation);
        return newOperation;
    }

	@Override
	public Page<Operation> getOperationsByCreationDate(Date createDate, Pageable pageable) {
		Page<Operation> operations = operationRepository.findByCreateDate(createDate, pageable);
		if (operations == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_OPERATION"), null);
		}

		return operations;
	}

    @Override
	public Operation getOperationById(Long id) {
		Operation operation = operationRepository
				.findByOperationId(id);
		if (operation == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get("MESSAGE_NOT_FOUND_OPERATION"), null);
		}
        List<OperationDetail> detailOperationList = operationDetailRepository.findByOperationId(id);
        operation.setOperationDetails(detailOperationList);

		return operation;
	}

	@Override
	public List<OperationType> getOperationTypes() {
		List<OperationType> operationTypes = Arrays.asList(OperationType.values());
        return operationTypes;
	}

	@Override
	public List<PaymentMethod> getPaymentMethods() {
		List<PaymentMethod> paymentMethods = Arrays.asList(PaymentMethod.values());
        return paymentMethods;
	}

    @Override
    public Operation cancelOperation(Long id) {
        Operation operation = operationRepository.findByOperationId(id);
        this.disabledOperation(operation);
        return operationRepository.save(operation);
    }

    private void disabledOperation(Operation operation) {
        operation.setDisabled(true);
        operation.setDisabledDate(new Date());
        operation.setOperationStatus(OperationStatus.CANCELED);
    }

    private void updateArticles(List<OperationDetail> operationDetails, Operation newOperation) {
        logger.info("Cantidad de detalles de operacion es " + operationDetails.size() + " para la operation con id " + newOperation.getOperationId());
        for (OperationDetail detailOperation : operationDetails) {
            detailOperation.setOperationId(newOperation
                    .getOperationId());
            operationDetailRepository.save(detailOperation);

            // actualizo el stock del producto
            Article article = articleRepository
                    .findByArticleId(detailOperation.getArticleId());
            double newQuantityArticle = (article.getCurrentQuantity() - detailOperation.getAmount());
            article.setCurrentQuantity(newQuantityArticle);
            articleRepository.save(article);
            logger.info("Stock de Articulo actualizado con exito: " + article.toString());

        }
    }

}
