
package com.luxoft.mfcautotests.services.dashboard.jsonresponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "isInProgress",
    "dateInProgress",
    "hint",
    "date",
    "extraInfo",
    "valueGroups"
})
public class Indicator {

    /**
     * Уникальный идентификатор показателя
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u044b\u0439 \u0438\u0434\u0435\u043d\u0442\u0438\u0444\u0438\u043a\u0430\u0442\u043e\u0440 \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f")
    private Integer id;
    /**
     * Наименование показателя
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("\u041d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0435 \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f")
    private String name;
    /**
     * Признак того, что показатель находится в раработке
     * (Required)
     * 
     */
    @JsonProperty("isInProgress")
    @JsonPropertyDescription("\u041f\u0440\u0438\u0437\u043d\u0430\u043a \u0442\u043e\u0433\u043e, \u0447\u0442\u043e \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044c \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u0432 \u0440\u0430\u0440\u0430\u0431\u043e\u0442\u043a\u0435")
    private Boolean isInProgress;
    /**
     * Дата окончания разработки, если показатель находится в разработке. Указывается, если поле isInProgress = true
     * (Required)
     * 
     */
    @JsonProperty("dateInProgress")
    @JsonPropertyDescription("\u0414\u0430\u0442\u0430 \u043e\u043a\u043e\u043d\u0447\u0430\u043d\u0438\u044f \u0440\u0430\u0437\u0440\u0430\u0431\u043e\u0442\u043a\u0438, \u0435\u0441\u043b\u0438 \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044c \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u0432 \u0440\u0430\u0437\u0440\u0430\u0431\u043e\u0442\u043a\u0435. \u0423\u043a\u0430\u0437\u044b\u0432\u0430\u0435\u0442\u0441\u044f, \u0435\u0441\u043b\u0438 \u043f\u043e\u043b\u0435 isInProgress = true")
    private String dateInProgress;
    /**
     * Методика расчета показателя
     * 
     */
    @JsonProperty("hint")
    @JsonPropertyDescription("\u041c\u0435\u0442\u043e\u0434\u0438\u043a\u0430 \u0440\u0430\u0441\u0447\u0435\u0442\u0430 \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f")
    private String hint;
    /**
     * Дата актуальности значения показателя в формате dd.mm.yyyyy
     * (Required)
     * 
     */
    @JsonProperty("date")
    @JsonPropertyDescription("\u0414\u0430\u0442\u0430 \u0430\u043a\u0442\u0443\u0430\u043b\u044c\u043d\u043e\u0441\u0442\u0438 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f \u0432 \u0444\u043e\u0440\u043c\u0430\u0442\u0435 dd.mm.yyyyy")
    private String date;
    /**
     * Дополнительная информация о показателе
     * 
     */
    @JsonProperty("extraInfo")
    @JsonPropertyDescription("\u0414\u043e\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044c\u043d\u0430\u044f \u0438\u043d\u0444\u043e\u0440\u043c\u0430\u0446\u0438\u044f \u043e \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u0435")
    private String extraInfo;
    /**
     * Описание значений показателей
     * (Required)
     * 
     */
    @JsonProperty("valueGroups")
    @JsonPropertyDescription("\u041e\u043f\u0438\u0441\u0430\u043d\u0438\u0435 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0439 \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u0435\u0439")
    private List<ValueGroup> valueGroups = new ArrayList<ValueGroup>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Уникальный идентификатор показателя
     * (Required)
     * 
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * Уникальный идентификатор показателя
     * (Required)
     * 
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Наименование показателя
     * (Required)
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Наименование показателя
     * (Required)
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Признак того, что показатель находится в раработке
     * (Required)
     * 
     */
    @JsonProperty("isInProgress")
    public Boolean getIsInProgress() {
        return isInProgress;
    }

    /**
     * Признак того, что показатель находится в раработке
     * (Required)
     * 
     */
    @JsonProperty("isInProgress")
    public void setIsInProgress(Boolean isInProgress) {
        this.isInProgress = isInProgress;
    }

    /**
     * Дата окончания разработки, если показатель находится в разработке. Указывается, если поле isInProgress = true
     * (Required)
     * 
     */
    @JsonProperty("dateInProgress")
    public String getDateInProgress() {
        return dateInProgress;
    }

    /**
     * Дата окончания разработки, если показатель находится в разработке. Указывается, если поле isInProgress = true
     * (Required)
     * 
     */
    @JsonProperty("dateInProgress")
    public void setDateInProgress(String dateInProgress) {
        this.dateInProgress = dateInProgress;
    }

    /**
     * Методика расчета показателя
     * 
     */
    @JsonProperty("hint")
    public String getHint() {
        return hint;
    }

    /**
     * Методика расчета показателя
     * 
     */
    @JsonProperty("hint")
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * Дата актуальности значения показателя в формате dd.mm.yyyyy
     * (Required)
     * 
     */
    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    /**
     * Дата актуальности значения показателя в формате dd.mm.yyyyy
     * (Required)
     * 
     */
    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Дополнительная информация о показателе
     * 
     */
    @JsonProperty("extraInfo")
    public String getExtraInfo() {
        return extraInfo;
    }

    /**
     * Дополнительная информация о показателе
     * 
     */
    @JsonProperty("extraInfo")
    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    /**
     * Описание значений показателей
     * (Required)
     * 
     */
    @JsonProperty("valueGroups")
    public List<ValueGroup> getValueGroups() {
        return valueGroups;
    }

    /**
     * Описание значений показателей
     * (Required)
     * 
     */
    @JsonProperty("valueGroups")
    public void setValueGroups(List<ValueGroup> valueGroups) {
        this.valueGroups = valueGroups;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(isInProgress).append(dateInProgress).append(hint).append(date).append(extraInfo).append(valueGroups).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Indicator) == false) {
            return false;
        }
        Indicator rhs = ((Indicator) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(isInProgress, rhs.isInProgress).append(dateInProgress, rhs.dateInProgress).append(hint, rhs.hint).append(date, rhs.date).append(extraInfo, rhs.extraInfo).append(valueGroups, rhs.valueGroups).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
