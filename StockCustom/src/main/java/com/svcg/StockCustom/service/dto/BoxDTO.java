package com.svcg.StockCustom.service.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.svcg.StockCustom.entity.Operation;

public class BoxDTO {
	
	private Long boxId;

    private Double cashOpen;

    @Temporal(TemporalType.DATE)
    private Date dateOpen;

    private Double cashClose;

    @Temporal(TemporalType.DATE)
    private Date dateClose;

    private Boolean open;

    @Size( max = 255)
    private String detailClose;

	private List<Operation> operationList = new ArrayList<>();
	
	public BoxDTO() {
		super();
	}

	public BoxDTO(Long boxId, Double cashOpen, Date dateOpen, Double cashClose, Date dateClose, Boolean open,
			@Size(max = 255) String detailClose, List<Operation> operationList) {
		super();
		this.boxId = boxId;
		this.cashOpen = cashOpen;
		this.dateOpen = dateOpen;
		this.cashClose = cashClose;
		this.dateClose = dateClose;
		this.open = open;
		this.detailClose = detailClose;
		this.operationList = operationList;
	}

	public Long getBoxId() {
		return boxId;
	}

	public void setBoxId(Long boxId) {
		this.boxId = boxId;
	}

	public Double getCashOpen() {
		return cashOpen;
	}

	public void setCashOpen(Double cashOpen) {
		this.cashOpen = cashOpen;
	}

	public Date getDateOpen() {
		return dateOpen;
	}

	public void setDateOpen(Date dateOpen) {
		this.dateOpen = dateOpen;
	}

	public Double getCashClose() {
		return cashClose;
	}

	public void setCashClose(Double cashClose) {
		this.cashClose = cashClose;
	}

	public Date getDateClose() {
		return dateClose;
	}

	public void setDateClose(Date dateClose) {
		this.dateClose = dateClose;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getDetailClose() {
		return detailClose;
	}

	public void setDetailClose(String detailClose) {
		this.detailClose = detailClose;
	}

	public List<Operation> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<Operation> operationList) {
		this.operationList = operationList;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getBoxId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
		BoxDTO boxDTO = (BoxDTO) obj;
		if (getBoxId() == null || boxDTO.getBoxId() != null) {
			return false;
		}
		return Objects.equals(getBoxId(), boxDTO.getBoxId());
	}

	@Override
	public String toString() {
		return "BoxDTO [boxId=" + boxId + ", cashOpen=" + cashOpen + ", dateOpen=" + dateOpen + ", cashClose="
				+ cashClose + ", dateClose=" + dateClose + ", open=" + open + ", detailClose=" + detailClose
				+ ", operationList=" + operationList + "]";
	}
    
    
}
