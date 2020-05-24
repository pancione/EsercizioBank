package com.panc.bank.action.bonifico.out;

import com.panc.bank.entity.Amount;
import com.panc.bank.entity.Creditor;
import com.panc.bank.entity.Debtor;
import com.panc.bank.entity.Fees;
import com.panc.bank.entity.LegalPersonBeneficiary;
import com.panc.bank.entity.TaxRelief;

import java.util.List;

public class BonificoOut {

	private String moneyTransferId;
	private String status;
	private String direction;
	private Creditor creditor;
	private Debtor debtor;
	private String cro;
	private String uri;
	private String trn;
	private String description;
	private String createDatetime;
	private String accountedDatetime;
	private String debtorValueDate;
	private String creditorValueDate;
	private Amount amount;
	private boolean isUrgent;
	private boolean isInstant;
	private String feeType;
	private String feeAccountId;
	private List<Fees> fees;
	private TaxRelief taxRelief;	  
	private LegalPersonBeneficiary legalPersonBeneficiary;
	public String getMoneyTransferId() {
		return moneyTransferId;
	}
	public void setMoneyTransferId(String moneyTransferId) {
		this.moneyTransferId = moneyTransferId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Creditor getCreditor() {
		return creditor;
	}
	public void setCreditor(Creditor creditor) {
		this.creditor = creditor;
	}
	public Debtor getDebtor() {
		return debtor;
	}
	public void setDebtor(Debtor debtor) {
		this.debtor = debtor;
	}
	public String getCro() {
		return cro;
	}
	public void setCro(String cro) {
		this.cro = cro;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getTrn() {
		return trn;
	}
	public void setTrn(String trn) {
		this.trn = trn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(String createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getAccountedDatetime() {
		return accountedDatetime;
	}
	public void setAccountedDatetime(String accountedDatetime) {
		this.accountedDatetime = accountedDatetime;
	}
	public String getDebtorValueDate() {
		return debtorValueDate;
	}
	public void setDebtorValueDate(String debtorValueDate) {
		this.debtorValueDate = debtorValueDate;
	}
	public String getCreditorValueDate() {
		return creditorValueDate;
	}
	public void setCreditorValueDate(String creditorValueDate) {
		this.creditorValueDate = creditorValueDate;
	}
	public Amount getAmount() {
		return amount;
	}
	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	public boolean isUrgent() {
		return isUrgent;
	}
	public void setUrgent(boolean isUrgent) {
		this.isUrgent = isUrgent;
	}
	public boolean isInstant() {
		return isInstant;
	}
	public void setInstant(boolean isInstant) {
		this.isInstant = isInstant;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getFeeAccountId() {
		return feeAccountId;
	}
	public void setFeeAccountId(String feeAccountId) {
		this.feeAccountId = feeAccountId;
	}
	public List<Fees> getFees() {
		return fees;
	}
	public void setFees(List<Fees> fees) {
		this.fees = fees;
	}
	public TaxRelief getTaxRelief() {
		return taxRelief;
	}
	public void setTaxRelief(TaxRelief taxRelief) {
		this.taxRelief = taxRelief;
	}
	public LegalPersonBeneficiary getLegalPersonBeneficiary() {
		return legalPersonBeneficiary;
	}
	public void setLegalPersonBeneficiary(LegalPersonBeneficiary legalPersonBeneficiary) {
		this.legalPersonBeneficiary = legalPersonBeneficiary;
	}
	
}
