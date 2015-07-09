package br.com.exemplos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "log_table_column")
@SequenceGenerator(name = "sequenceLTC", sequenceName = "ltc_id_seq")
public class LogTableColumn {

	@Id
	@GeneratedValue(generator = "sequenceLTC", strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "table_name")
	private String table;

	@Column(name = "column_name")
	private String column;

	@Column(name = "old_value")
	private String oldValue;

	@Column(name = "new_value")
	private String newValue;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_mod")
	private Date dataModificacao;
	
	@Column(name = "record_id")
	private long recordId;
	
	public LogTableColumn() {
		dataModificacao = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	public long getRecordId() {
		return recordId;
	}
	
	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogTableColumn [id=");
		builder.append(id);
		builder.append(", table=");
		builder.append(table);
		builder.append(", recordId=");
		builder.append(recordId);
		builder.append(", column=");
		builder.append(column);
		builder.append(", oldValue=");
		builder.append(oldValue);
		builder.append(", newValue=");
		builder.append(newValue);
		builder.append(", dataModificacao=");
		builder.append(dataModificacao);
		builder.append("]");
		return builder.toString();
	}

	
}
