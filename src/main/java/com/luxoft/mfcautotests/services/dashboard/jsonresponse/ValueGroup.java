
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
    "values"
})
public class ValueGroup {

    /**
     * Уникальный идентификатор групп значений показателя
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u044b\u0439 \u0438\u0434\u0435\u043d\u0442\u0438\u0444\u0438\u043a\u0430\u0442\u043e\u0440 \u0433\u0440\u0443\u043f\u043f \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0439 \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f")
    private Integer id;
    /**
     * Наименование группы значений показателей
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("\u041d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0435 \u0433\u0440\u0443\u043f\u043f\u044b \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0439 \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u0435\u0439")
    private String name;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("values")
    private List<Value> values = new ArrayList<Value>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Уникальный идентификатор групп значений показателя
     * (Required)
     * 
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * Уникальный идентификатор групп значений показателя
     * (Required)
     * 
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Наименование группы значений показателей
     * (Required)
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Наименование группы значений показателей
     * (Required)
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("values")
    public List<Value> getValues() {
        return values;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("values")
    public void setValues(List<Value> values) {
        this.values = values;
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
        return new HashCodeBuilder().append(id).append(name).append(values).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ValueGroup) == false) {
            return false;
        }
        ValueGroup rhs = ((ValueGroup) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(values, rhs.values).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
