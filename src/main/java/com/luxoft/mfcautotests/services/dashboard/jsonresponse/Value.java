
package com.luxoft.mfcautotests.services.dashboard.jsonresponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "type",
    "typeName",
    "units",
    "valueDescription",
    "dynamicsVector",
    "value",
    "dynamics"
})
public class Value {

    /**
     * Идентификатор типа значения показателей, если значения одного и тогоже наименования показателя передаются в разных единицах измерения
     * (Required)
     * 
     */
    @JsonProperty("type")
    @JsonPropertyDescription("\u0418\u0434\u0435\u043d\u0442\u0438\u0444\u0438\u043a\u0430\u0442\u043e\u0440 \u0442\u0438\u043f\u0430 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u0435\u0439, \u0435\u0441\u043b\u0438 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043e\u0434\u043d\u043e\u0433\u043e \u0438 \u0442\u043e\u0433\u043e\u0436\u0435 \u043d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f \u043f\u0435\u0440\u0435\u0434\u0430\u044e\u0442\u0441\u044f \u0432 \u0440\u0430\u0437\u043d\u044b\u0445 \u0435\u0434\u0438\u043d\u0438\u0446\u0430\u0445 \u0438\u0437\u043c\u0435\u0440\u0435\u043d\u0438\u044f")
    private Integer type;
    /**
     * Наименование типа значения показателей
     * (Required)
     * 
     */
    @JsonProperty("typeName")
    @JsonPropertyDescription("\u041d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0435 \u0442\u0438\u043f\u0430 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u0435\u0439")
    private String typeName;
    /**
     * Единица измерения значения показателя
     * 
     */
    @JsonProperty("units")
    @JsonPropertyDescription("\u0415\u0434\u0438\u043d\u0438\u0446\u0430 \u0438\u0437\u043c\u0435\u0440\u0435\u043d\u0438\u044f \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f")
    private String units;
    /**
     * Описание значения показателя
     * 
     */
    @JsonProperty("valueDescription")
    @JsonPropertyDescription("\u041e\u043f\u0438\u0441\u0430\u043d\u0438\u0435 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f")
    private String valueDescription;
    /**
     * Характеристика текущего значения показателя: positive - будет зеленое, negative - будет красное, any_deviation - будет серое
     * (Required)
     * 
     */
    @JsonProperty("dynamicsVector")
    @JsonPropertyDescription("\u0425\u0430\u0440\u0430\u043a\u0442\u0435\u0440\u0438\u0441\u0442\u0438\u043a\u0430 \u0442\u0435\u043a\u0443\u0449\u0435\u0433\u043e \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f: positive - \u0431\u0443\u0434\u0435\u0442 \u0437\u0435\u043b\u0435\u043d\u043e\u0435, negative - \u0431\u0443\u0434\u0435\u0442 \u043a\u0440\u0430\u0441\u043d\u043e\u0435, any_deviation - \u0431\u0443\u0434\u0435\u0442 \u0441\u0435\u0440\u043e\u0435")
    private Value.DynamicsVector dynamicsVector;
    /**
     * Значение показателя
     * (Required)
     * 
     */
    @JsonProperty("value")
    @JsonPropertyDescription("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f")
    private Double value;
    /**
     * Описание изменения значений показателей
     * 
     */
    @JsonProperty("dynamics")
    @JsonPropertyDescription("\u041e\u043f\u0438\u0441\u0430\u043d\u0438\u0435 \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u044f \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0439 \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u0435\u0439")
    private List<Dynamic> dynamics = new ArrayList<Dynamic>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Идентификатор типа значения показателей, если значения одного и тогоже наименования показателя передаются в разных единицах измерения
     * (Required)
     * 
     */
    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    /**
     * Идентификатор типа значения показателей, если значения одного и тогоже наименования показателя передаются в разных единицах измерения
     * (Required)
     * 
     */
    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * Наименование типа значения показателей
     * (Required)
     * 
     */
    @JsonProperty("typeName")
    public String getTypeName() {
        return typeName;
    }

    /**
     * Наименование типа значения показателей
     * (Required)
     * 
     */
    @JsonProperty("typeName")
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Единица измерения значения показателя
     * 
     */
    @JsonProperty("units")
    public String getUnits() {
        return units;
    }

    /**
     * Единица измерения значения показателя
     * 
     */
    @JsonProperty("units")
    public void setUnits(String units) {
        this.units = units;
    }

    /**
     * Описание значения показателя
     * 
     */
    @JsonProperty("valueDescription")
    public String getValueDescription() {
        return valueDescription;
    }

    /**
     * Описание значения показателя
     * 
     */
    @JsonProperty("valueDescription")
    public void setValueDescription(String valueDescription) {
        this.valueDescription = valueDescription;
    }

    /**
     * Характеристика текущего значения показателя: positive - будет зеленое, negative - будет красное, any_deviation - будет серое
     * (Required)
     * 
     */
    @JsonProperty("dynamicsVector")
    public Value.DynamicsVector getDynamicsVector() {
        return dynamicsVector;
    }

    /**
     * Характеристика текущего значения показателя: positive - будет зеленое, negative - будет красное, any_deviation - будет серое
     * (Required)
     * 
     */
    @JsonProperty("dynamicsVector")
    public void setDynamicsVector(Value.DynamicsVector dynamicsVector) {
        this.dynamicsVector = dynamicsVector;
    }

    /**
     * Значение показателя
     * (Required)
     * 
     */
    @JsonProperty("value")
    public Double getValue() {
        return value;
    }

    /**
     * Значение показателя
     * (Required)
     * 
     */
    @JsonProperty("value")
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * Описание изменения значений показателей
     * 
     */
    @JsonProperty("dynamics")
    public List<Dynamic> getDynamics() {
        return dynamics;
    }

    /**
     * Описание изменения значений показателей
     * 
     */
    @JsonProperty("dynamics")
    public void setDynamics(List<Dynamic> dynamics) {
        this.dynamics = dynamics;
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
        return new HashCodeBuilder().append(type).append(typeName).append(units).append(valueDescription).append(dynamicsVector).append(value).append(dynamics).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Value) == false) {
            return false;
        }
        Value rhs = ((Value) other);
        return new EqualsBuilder().append(type, rhs.type).append(typeName, rhs.typeName).append(units, rhs.units).append(valueDescription, rhs.valueDescription).append(dynamicsVector, rhs.dynamicsVector).append(value, rhs.value).append(dynamics, rhs.dynamics).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

    @XmlType(name = "DynamicsVector1")
    public enum DynamicsVector {

        POSITIVE("positive"),
        NEGATIVE("negative"),
        ANY_DEVIATION("any_deviation"),
        POSITIVE_("POSITIVE"),
        NEGATIVE_("NEGATIVE"),
        ANY_DEVIATION_("ANY_DEVIATION");
        private final String value;
        private final static Map<String, Value.DynamicsVector> CONSTANTS = new HashMap<String, Value.DynamicsVector>();

        static {
            for (Value.DynamicsVector c: values()) {
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
        public static Value.DynamicsVector fromValue(String value) {
            Value.DynamicsVector constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
