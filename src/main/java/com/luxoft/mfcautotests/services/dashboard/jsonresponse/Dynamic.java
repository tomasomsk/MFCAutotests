
package com.luxoft.mfcautotests.services.dashboard.jsonresponse;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "period",
    "value",
    "date",
    "dynamicsVector"
})
public class Dynamic {

    /**
     * Уникальный идентификатор изменения значения показателя
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u044b\u0439 \u0438\u0434\u0435\u043d\u0442\u0438\u0444\u0438\u043a\u0430\u0442\u043e\u0440 \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u044f \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f")
    private Integer id;
    /**
     * Наименование периода, за который передается изменение значения показателя
     * (Required)
     * 
     */
    @JsonProperty("period")
    @JsonPropertyDescription("\u041d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0435 \u043f\u0435\u0440\u0438\u043e\u0434\u0430, \u0437\u0430 \u043a\u043e\u0442\u043e\u0440\u044b\u0439 \u043f\u0435\u0440\u0435\u0434\u0430\u0435\u0442\u0441\u044f \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u0435 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f")
    private String period;
    /**
     * Значения изменения показателя
     * (Required)
     * 
     */
    @JsonProperty("value")
    @JsonPropertyDescription("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f")
    private Double value;
    /**
     * Дата, на которую актуальна изменение значение показателя в формате dd.mm.yyy
     * (Required)
     * 
     */
    @JsonProperty("date")
    @JsonPropertyDescription("\u0414\u0430\u0442\u0430, \u043d\u0430 \u043a\u043e\u0442\u043e\u0440\u0443\u044e \u0430\u043a\u0442\u0443\u0430\u043b\u044c\u043d\u0430 \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u0435 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f \u0432 \u0444\u043e\u0440\u043c\u0430\u0442\u0435 dd.mm.yyy")
    private String date;
    /**
     * Характеристика динамики изменения значения показателя: positive - будет зеленое, negative - будет красное, any_deviation - будет серое
     * (Required)
     * 
     */
    @JsonProperty("dynamicsVector")
    @JsonPropertyDescription("\u0425\u0430\u0440\u0430\u043a\u0442\u0435\u0440\u0438\u0441\u0442\u0438\u043a\u0430 \u0434\u0438\u043d\u0430\u043c\u0438\u043a\u0438 \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u044f \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f: positive - \u0431\u0443\u0434\u0435\u0442 \u0437\u0435\u043b\u0435\u043d\u043e\u0435, negative - \u0431\u0443\u0434\u0435\u0442 \u043a\u0440\u0430\u0441\u043d\u043e\u0435, any_deviation - \u0431\u0443\u0434\u0435\u0442 \u0441\u0435\u0440\u043e\u0435")
    private Dynamic.DynamicsVector dynamicsVector;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Уникальный идентификатор изменения значения показателя
     * (Required)
     * 
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * Уникальный идентификатор изменения значения показателя
     * (Required)
     * 
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Наименование периода, за который передается изменение значения показателя
     * (Required)
     * 
     */
    @JsonProperty("period")
    public String getPeriod() {
        return period;
    }

    /**
     * Наименование периода, за который передается изменение значения показателя
     * (Required)
     * 
     */
    @JsonProperty("period")
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Значения изменения показателя
     * (Required)
     * 
     */
    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    /**
     * Значения изменения показателя
     * (Required)
     * 
     */
    @JsonProperty("value")
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * Дата, на которую актуальна изменение значение показателя в формате dd.mm.yyy
     * (Required)
     * 
     */
    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    /**
     * Дата, на которую актуальна изменение значение показателя в формате dd.mm.yyy
     * (Required)
     * 
     */
    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Характеристика динамики изменения значения показателя: positive - будет зеленое, negative - будет красное, any_deviation - будет серое
     * (Required)
     * 
     */
    @JsonProperty("dynamicsVector")
    public Dynamic.DynamicsVector getDynamicsVector() {
        return dynamicsVector;
    }

    /**
     * Характеристика динамики изменения значения показателя: positive - будет зеленое, negative - будет красное, any_deviation - будет серое
     * (Required)
     * 
     */
    @JsonProperty("dynamicsVector")
    public void setDynamicsVector(Dynamic.DynamicsVector dynamicsVector) {
        this.dynamicsVector = dynamicsVector;
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
        return new HashCodeBuilder().append(id).append(period).append(value).append(date).append(dynamicsVector).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Dynamic) == false) {
            return false;
        }
        Dynamic rhs = ((Dynamic) other);
        return new EqualsBuilder().append(id, rhs.id).append(period, rhs.period).append(value, rhs.value).append(date, rhs.date).append(dynamicsVector, rhs.dynamicsVector).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

    @XmlType(name = "DynamicsVector2")
    public enum DynamicsVector {

        POSITIVE("positive"),
        NEGATIVE("negative"),
        ANY_DEVIATION("any_deviation"),
        POSITIVE_("POSITIVE"),
        NEGATIVE_("NEGATIVE"),
        ANY_DEVIATION_("ANY_DEVIATION");
        private final String value;
        private final static Map<String, Dynamic.DynamicsVector> CONSTANTS = new HashMap<String, Dynamic.DynamicsVector>();

        static {
            for (Dynamic.DynamicsVector c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private DynamicsVector(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static Dynamic.DynamicsVector fromValue(String value) {
            Dynamic.DynamicsVector constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
