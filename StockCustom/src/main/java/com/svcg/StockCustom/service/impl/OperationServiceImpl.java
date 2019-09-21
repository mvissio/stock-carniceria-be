package com.svcg.StockCustom.service.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.svcg.StockCustom.entity.Box;
import com.svcg.StockCustom.repository.BoxRepository;
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
import com.svcg.StockCustom.service.dto.MonthlyOperationsReportDTO;
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
	@Qualifier("boxRepository")
	private BoxRepository boxRepository;

	@Autowired
	Messages messages;

	@Autowired
	private OperationConverter operationConverter;

	@Autowired
	private OperationDetailConverter operationDetailConverter;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	@Transactional
	public OperationDTO saveOperation(OperationDTO operationDTO) {
		// como manejo el estado por ahora viene desde el front, el total , el subtotal
		// ??
		Box boxActive = boxRepository.findFirstByOpenTrueOrderByDateOpenDesc();

		if (boxActive == null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_BOXS_OPEN));
		}
		operationDTO.setCreateDate(new Date());
		operationDTO.setBoxId(boxActive.getBoxId());
		operationDTO.setCreateDateTime(new Date());
		OperationDTO newOperationDTO = this.operationConverter
				.toDTO(this.operationRepository.save(this.operationConverter.toEntity(operationDTO)));
		logger.info("Operacion guardada con exito: {}", newOperationDTO.toString());

		if (newOperationDTO != null) {
			List<OperationDetailDTO> operationDetailsDTO = operationDTO.getOperationDetails();

			// que sucede en el caso que se estan registrando despertidisios ??
			// aplica ??
			if (!operationDetailsDTO.isEmpty()) {
				this.updateArticles(this.operationDetailConverter.toEntity(operationDetailsDTO),
						this.operationConverter.toEntity(newOperationDTO));
			}
		}
		return newOperationDTO;
	}

	@Override
	@Transactional
	public OperationDTO updateOperation(OperationDTO operationDTO) {
		Box boxActive = boxRepository.findFirstByOpenTrueOrderByDateOpenDesc();

		if (boxActive == null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_BOXS_OPEN));
		}
		Optional<Operation> oldOperation = this.operationRepository.findByOperationId(operationDTO.getOperationId());
		Operation newOperation = new Operation();
		newOperation.setCreateDate(new Date());
		newOperation.setCreateDateTime(new Date());
		newOperation.setBoxId(boxActive.getBoxId());
		newOperation.setOperationStatus(operationDTO.getOperationStatus());
		newOperation.setDisabled(operationDTO.getDisabled());
		newOperation.setOperationDetails(this.operationDetailConverter.toEntity(operationDTO.getOperationDetails()));
		newOperation.setOperationType(operationDTO.getOperationType());
		newOperation.setPaymentMethod(operationDTO.getPaymentMethod());
		newOperation.setSubTotal(operationDTO.getSubTotal());
		newOperation.setTotal(operationDTO.getTotal());

		List<OperationDetail> operationDetails = newOperation.getOperationDetails();
		oldOperation.get().getOperationDetails().forEach(opd -> {
			Optional<Article> article = articleRepository.findByArticleId(opd.getArticleId());
			if (article.isPresent() && opd.getArticleId().equals(opd.getArticleId())) {
				article.get().setCurrentQuantity(article.get().getCurrentQuantity() + opd.getAmount());
				articleRepository.save(article.get());
				logger.info("Stock de Articulo restaurado con exito: {}", article);
			}
		});
		newOperation = this.operationRepository.save(newOperation);
		this.updateArticles(operationDetails, newOperation);
		this.disabledOperation(oldOperation.get());
		updateArticles(oldOperation.get().getOperationDetails(), oldOperation.get());
		this.operationRepository.save(oldOperation.get());
		return this.operationConverter.toDTO(newOperation);
	}

	@Override
	public OperationDTO getOperationById(Long id) {
		Optional<Operation> operation = this.operationRepository.findByOperationId(id);
		if (!operation.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}
		List<OperationDetail> detailOperationList = operationDetailRepository.findByOperationId(id);
		operation.get().setOperationDetails(detailOperationList);

		return this.operationConverter.toDTO(operation.get());
	}

	@Override
	public List<OperationType> getOperationTypes() {
		return Arrays.asList(OperationType.values());
	}

	@Override
	public List<PaymentMethod> getPaymentMethods() {
		return Arrays.asList(PaymentMethod.values());
	}

	@Override
	public OperationDTO cancelOperation(Long id) {
		Optional<Operation> operation = this.operationRepository.findByOperationId(id);
		this.disabledOperation(operation.get());
		this.updateArticles(operation.get().getOperationDetails(), operation.get());
		return this.operationConverter.toDTO(this.operationRepository.save(operation.get()));
	}

	private void disabledOperation(Operation operation) {
		operation.setDisabled(true);
		operation.setDisabledDate(new Date());
		operation.setOperationStatus(OperationStatus.CANCELED);
	}

	private void updateArticles(List<OperationDetail> operationDetails, Operation newOperation) {
		logger.info("Cantidad de detalles de operacion es {}  para la operation con id {}", operationDetails.size(),
				newOperation.getOperationId());
		operationDetails = (newOperation.isDisabled())
				? operationDetailRepository.findByOperationId(newOperation.getOperationId())
				: operationDetails;
		for (OperationDetail detailOperation : operationDetails) {
			detailOperation.setOperationId(newOperation.getOperationId());
			operationDetailRepository.save(detailOperation);

			// actualizo el stock del producto
			Optional<Article> article = articleRepository.findByArticleId(detailOperation.getArticleId());
			if (article.isPresent()) {
				Double newQuantityArticle;
				switch (newOperation.getOperationType()) {
				case BUY:
					newQuantityArticle = (newOperation.isDisabled())
							? (article.get().getCurrentQuantity() - detailOperation.getAmount())
							: (article.get().getCurrentQuantity() + detailOperation.getAmount());
					break;
				case SALE:
					newQuantityArticle = (newOperation.isDisabled())
							? (article.get().getCurrentQuantity() + detailOperation.getAmount())
							: (article.get().getCurrentQuantity() - detailOperation.getAmount());
					break;
				default:
					newQuantityArticle = (double) 0;
				}
				article.get().setCurrentQuantity(newQuantityArticle);
				articleRepository.save(article.get());
				logger.info("Stock de Articulo actualizado con exito: {}", article.get().toString());
			}
		}
	}

	// *********************BUSQUEDA POR TIPO, PAYMENT
	// METHOD***************************

	@Override
	public Page<OperationDTO> getOperationsByCreateDate(Date createDate, Pageable pageable) {
		Optional<Page<Operation>> operations = this.operationRepository.findByCreateDate(createDate, pageable);
		if (!operations.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.get().map(this.operationConverter::toDTO);
	}

	@Override
	public Page<OperationDTO> getOperationsByOperationType(OperationType operationType, Pageable pageable) {
		Optional<Page<Operation>> operations = operationRepository.findByOperationType(operationType, pageable);
		if (!operations.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.get().map(this.operationConverter::toDTO);
	}

	@Override
	public Page<OperationDTO> getOperationsPaymentMethod(PaymentMethod paymentMethod, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<OperationDTO> getOperationsByOperationTypeAndPaymentMethod(Date createDate, PaymentMethod paymentMethod,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	// *********************BUSQUEDA POR UNA SOLA FECHA CREATED DATE Y TIPO, PAYMENT
	// METHOD***************************

	@Override
	public Page<OperationDTO> getOperationsByCreateDateAndOperationType(Date createDate, OperationType operationType,
			Pageable pageable) {
		Optional<Page<Operation>> operations = operationRepository.findByCreateDateAndOperationType(createDate,
				operationType, pageable);
		if (operations.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.get().map(this.operationConverter::toDTO);
	}

	@Override
	public Page<OperationDTO> getOperationsByCreateDateAndPaymentMethod(Date createDate, PaymentMethod paymentMethod,
			Pageable pageable) {
		Optional<Page<Operation>> operations = operationRepository.findByCreateDateAndPaymentMethod(createDate,
				paymentMethod, pageable);
		if (!operations.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.get().map(this.operationConverter::toDTO);
	}

	// *********************BUSQUEDA POR PERIODOS CREATED DATE Y TIPO, PAYMENT
	// METHOD***************************

	@Override
	public Page<OperationDTO> getOperationsByCreateDateBetween(Date fromDate, Date toDate, Pageable pageable) {
		Optional<Page<Operation>> operations = operationRepository.findByCreateDateBetween(fromDate, toDate, pageable);
		if (!operations.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.get().map(this.operationConverter::toDTO);
	}

	@Override
	public Page<OperationDTO> getOperationsByCreateDateBetweenAndByOperationType(OperationType operationType,
			Date fromDate, Date toDate, Pageable pageable) {
		Optional<Page<Operation>> operations = operationRepository
				.findByOperationTypeAndCreateDateBetween(operationType, fromDate, toDate, pageable);
		if (!operations.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.get().map(this.operationConverter::toDTO);
	}

	@Override
	public Page<OperationDTO> getOperationsByCreateDateBetweenAndByPaymentMethod(PaymentMethod paymentMethod,
			Date fromDate, Date toDate, Pageable pageable) {
		Optional<Page<Operation>> operations = operationRepository
				.findByPaymentMethodAndCreateDateBetween(paymentMethod, fromDate, toDate, pageable);
		if (!operations.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.get().map(this.operationConverter::toDTO);
	}

	// *********************BUSQUEDA POR CLIENTE Y
	// PROVEEDORES***************************

	@Override
	public Page<OperationDTO> getOperationsByClientIdAndOperationType(Long clientId, OperationType operationType,
			Pageable pageable) {
		Optional<Page<Operation>> operations = operationRepository.findByClientIdAndOperationType(clientId,
				operationType, pageable);
		if (!operations.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.get().map(this.operationConverter::toDTO);
	}

	@Override
	public Page<OperationDTO> getOperationsByProviderIdAndOperationType(Long providerId, OperationType operationType,
			Pageable pageable) {
		Optional<Page<Operation>> operations = operationRepository.findByClientIdAndOperationType(providerId,
				operationType, pageable);
		if (!operations.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.get().map(this.operationConverter::toDTO);
	}

	@Override
	public Page<OperationDTO> getOperationsByClientIdAndPaymentMethod(Long clientId, PaymentMethod paymentMethod,
			Pageable pageable) {
		Optional<Page<Operation>> operations = operationRepository.findByClientIdAndPaymentMethod(clientId,
				paymentMethod, pageable);
		if (!operations.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.get().map(this.operationConverter::toDTO);
	}

	@Override
	public Page<OperationDTO> getOperationsByCreateDateAndPaymentMethodAndOperationType(Date createDate,
			PaymentMethod paymentMethod, OperationType operationType, Pageable pageable) {

		Optional<Page<Operation>> operations = operationRepository
				.findByCreateDateAndPaymentMethodAndOperationType(createDate, paymentMethod, operationType, pageable);
		if (!operations.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.get().map(this.operationConverter::toDTO);

	}

	@Override
	public Page<OperationDTO> getOperationsByPaymentMethodAndOperationTypeAndCreateDateBetween(
			PaymentMethod paymentMethod, OperationType operationType, Date fromDate, Date toDate, Pageable pageable) {

		Optional<Page<Operation>> operations = operationRepository
				.findByPaymentMethodAndOperationTypeAndCreateDateBetween(paymentMethod, operationType, fromDate, toDate,
						pageable);
		if (!operations.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					this.messages.get(Constant.MESSAGE_NOT_FOUND_OPERATION));
		}

		return operations.get().map(this.operationConverter::toDTO);

	}

	@Override
	public MonthlyOperationsReportDTO getMonthlyOperationsReport(int month, int year) {

		// obtengo cantidad de días maximos para el mes indicado
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH - 1, month);
		int numDays = calendar.getActualMaximum(Calendar.DATE);

		Date fromDate = new GregorianCalendar(year, month - 1, 1).getTime();
		Date toDate = new GregorianCalendar(year, month - 1, numDays).getTime();

		MonthlyOperationsReportDTO monthlyOperationsReportDTO = new MonthlyOperationsReportDTO();
		Optional<Double> totalSale = operationRepository.sumTotalOperation(fromDate, toDate, OperationType.SALE);
		Optional<Double> totalBuy = operationRepository.sumTotalOperation(fromDate, toDate, OperationType.BUY);
		if (totalBuy.isPresent()) {
			monthlyOperationsReportDTO.setTotalBuy(totalBuy.get());
		}
		if (totalSale.isPresent()) {
			monthlyOperationsReportDTO.setTotalSale(totalSale.get());
		}
		return monthlyOperationsReportDTO;
	}

}
