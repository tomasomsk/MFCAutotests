
package com.luxoft.mfcautotests.services.dashboard.jsonresponse;

import java.util.HashMap;
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
    "parameter",
    "value",
    "value1",
    "value2",
    "value3",
    "value4",
    "value5",
    "value6"
})
public class Detail {

    /**
     * Идентификатор значения. Должен быть уникальным среди всех уровней иерархии - как среди элементов массива, в котором находится, так и среди всех возможных дочерних значений
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("\u0418\u0434\u0435\u043d\u0442\u0438\u0444\u0438\u043a\u0430\u0442\u043e\u0440 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f. \u0414\u043e\u043b\u0436\u0435\u043d \u0431\u044b\u0442\u044c \u0443\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u044b\u043c \u0441\u0440\u0435\u0434\u0438 \u0432\u0441\u0435\u0445 \u0443\u0440\u043e\u0432\u043d\u0435\u0439 \u0438\u0435\u0440\u0430\u0440\u0445\u0438\u0438 - \u043a\u0430\u043a \u0441\u0440\u0435\u0434\u0438 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 \u043c\u0430\u0441\u0441\u0438\u0432\u0430, \u0432 \u043a\u043e\u0442\u043e\u0440\u043e\u043c \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f, \u0442\u0430\u043a \u0438 \u0441\u0440\u0435\u0434\u0438 \u0432\u0441\u0435\u0445 \u0432\u043e\u0437\u043c\u043e\u0436\u043d\u044b\u0445 \u0434\u043e\u0447\u0435\u0440\u043d\u0438\u0445 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0439")
    private Integer id;
    /**
     * Наименование для строки
     * 
     */
    @JsonProperty("parameter")
    @JsonPropertyDescription("\u041d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0435 \u0434\u043b\u044f \u0441\u0442\u0440\u043e\u043a\u0438")
    private String parameter;
    /**
     * Значение для первой колонки со значением
     * 
     */
    @JsonProperty("value")
    @JsonPropertyDescription("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0434\u043b\u044f \u043f\u0435\u0440\u0432\u043e\u0439 \u043a\u043e\u043b\u043e\u043d\u043a\u0438 \u0441\u043e \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435\u043c")
    private String value;
    /**
     * Значение для второй колонки со значением
     * 
     */
    @JsonProperty("value1")
    @JsonPropertyDescription("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0434\u043b\u044f \u0432\u0442\u043e\u0440\u043e\u0439 \u043a\u043e\u043b\u043e\u043d\u043a\u0438 \u0441\u043e \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435\u043c")
    private String value1;
    /**
     * Значение для третьей колонки со значением
     * 
     */
    @JsonProperty("value2")
    @JsonPropertyDescription("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0434\u043b\u044f \u0442\u0440\u0435\u0442\u044c\u0435\u0439 \u043a\u043e\u043b\u043e\u043d\u043a\u0438 \u0441\u043e \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435\u043c")
    private String value2;
    /**
     * Значение для четвертой колонки со значением
     * 
     */
    @JsonProperty("value3")
    @JsonPropertyDescription("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0434\u043b\u044f \u0447\u0435\u0442\u0432\u0435\u0440\u0442\u043e\u0439 \u043a\u043e\u043b\u043e\u043d\u043a\u0438 \u0441\u043e \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435\u043c")
    private String value3;
    /**
     * Значение для пятой колонки со значением
     * 
     */
    @JsonProperty("value4")
    @JsonPropertyDescription("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0434\u043b\u044f \u043f\u044f\u0442\u043e\u0439 \u043a\u043e\u043b\u043e\u043d\u043a\u0438 \u0441\u043e \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435\u043c")
    private String value4;
    /**
     * Значение для шестой колонки со значением
     * 
     */
    @JsonProperty("value5")
    @JsonPropertyDescription("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0434\u043b\u044f \u0448\u0435\u0441\u0442\u043e\u0439 \u043a\u043e\u043b\u043e\u043d\u043a\u0438 \u0441\u043e \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435\u043c")
    private String value5;
    /**
     * Значение для седьмой колонки со значением
     * 
     */
    @JsonProperty("value6")
    @JsonPropertyDescription("\u0417\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u0434\u043b\u044f \u0441\u0435\u0434\u044c\u043c\u043e\u0439 \u043a\u043e\u043b\u043e\u043d\u043a\u0438 \u0441\u043e \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435\u043c")
    private String value6;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Идентификатор значения. Должен быть уникальным среди всех уровней иерархии - как среди элементов массива, в котором находится, так и среди всех возможных дочерних значений
     * 
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * Идентификатор значения. Должен быть уникальным среди всех уровней иерархии - как среди элементов массива, в котором находится, так и среди всех возможных дочерних значений
     * 
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Наименование для строки
     * 
     */
    @JsonProperty("parameter")
    public String getParameter() {
        return parameter;
    }

    /**
     * Наименование для строки
     * 
     */
    @JsonProperty("parameter")
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Значение для первой колонки со значением
     * 
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
     * Значение для первой колонки со значением
     * 
     */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Значение для второй колонки со значением
     * 
     */
    @JsonProperty("value1")
    public String getValue1() {
        return value1;
    }

    /**
     * Значение для второй колонки со значением
     * 
     */
    @JsonProperty("value1")
    public void setValue1(String value1) {
        this.value1 = value1;
    }

    /**
     * Значение для третьей колонки со значением
     * 
     */
    @JsonProperty("value2")
    public String getValue2() {
        return value2;
    }

    /**
     * Значение для третьей колонки со значением
     * 
     */
    @JsonProperty("value2")
    public void setValue2(String value2) {
        this.value2 = value2;
    }

    /**
     * Значение для четвертой колонки со значением
     * 
     */
    @JsonProperty("value3")
    public String getValue3() {
        return value3;
    }

    /**
     * Значение для четвертой колонки со значением
     * 
     */
    @JsonProperty("value3")
    public void setValue3(String value3) {
        this.value3 = value3;
    }

    /**
     * Значение для пятой колонки со значением
     * 
     */
    @JsonProperty("value4")
    public String getValue4() {
        return value4;
    }

    /**
     * Значение для пятой колонки со значением
     * 
     */
    @JsonProperty("value4")
    public void setValue4(String value4) {
        this.value4 = value4;
    }

    /**
     * Значение для шестой колонки со значением
     * 
     */
    @JsonProperty("value5")
    public String getValue5() {
        return value5;
    }

    /**
     * Значение для шестой колонки со значением
     * 
     */
    @JsonProperty("value5")
    public void setValue5(String value5) {
        this.value5 = value5;
    }

    /**
     * Значение для седьмой колонки со значением
     * 
     */
    @JsonProperty("value6")
    public String getValue6() {
        return value6;
    }

    /**
     * Значение для седьмой колонки со значением
     * 
     */
    @JsonProperty("value6")
    public void setValue6(String value6) {
        this.value6 = value6;
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
        return new HashCodeBuilder().append(id).append(parameter).append(value).append(value1).append(value2).append(value3).append(value4).append(value5).append(value6).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Detail) == false) {
            return false;
        }
        Detail rhs = ((Detail) other);
        return new EqualsBuilder().append(id, rhs.id).append(parameter, rhs.parameter).append(value, rhs.value).append(value1, rhs.value1).append(value2, rhs.value2).append(value3, rhs.value3).append(value4, rhs.value4).append(value5, rhs.value5).append(value6, rhs.value6).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
