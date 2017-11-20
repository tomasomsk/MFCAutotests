
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
    "titles",
    "date",
    "details"
})
public class DetailInfo {

    /**
     * Идентификатор показателя. Совпадает с идентификатором показателя в main_info.schema
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("\u0418\u0434\u0435\u043d\u0442\u0438\u0444\u0438\u043a\u0430\u0442\u043e\u0440 \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f. \u0421\u043e\u0432\u043f\u0430\u0434\u0430\u0435\u0442 \u0441 \u0438\u0434\u0435\u043d\u0442\u0438\u0444\u0438\u043a\u0430\u0442\u043e\u0440\u043e\u043c \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f \u0432 main_info.schema")
    private Integer id;
    /**
     * Массив читабельных названий колонок для таблицы. Минимальное количество колонок - две, иначе таблица не получится
     * (Required)
     * 
     */
    @JsonProperty("titles")
    @JsonPropertyDescription("\u041c\u0430\u0441\u0441\u0438\u0432 \u0447\u0438\u0442\u0430\u0431\u0435\u043b\u044c\u043d\u044b\u0445 \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0439 \u043a\u043e\u043b\u043e\u043d\u043e\u043a \u0434\u043b\u044f \u0442\u0430\u0431\u043b\u0438\u0446\u044b. \u041c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043a\u043e\u043b\u043e\u043d\u043e\u043a - \u0434\u0432\u0435, \u0438\u043d\u0430\u0447\u0435 \u0442\u0430\u0431\u043b\u0438\u0446\u0430 \u043d\u0435 \u043f\u043e\u043b\u0443\u0447\u0438\u0442\u0441\u044f")
    private List<String> titles = new ArrayList<String>();
    /**
     * Дата актуальности значения показателя в формате dd.mm.yyyyy
     * (Required)
     * 
     */
    @JsonProperty("date")
    @JsonPropertyDescription("\u0414\u0430\u0442\u0430 \u0430\u043a\u0442\u0443\u0430\u043b\u044c\u043d\u043e\u0441\u0442\u0438 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u044f \u043f\u043e\u043a\u0430\u0437\u0430\u0442\u0435\u043b\u044f \u0432 \u0444\u043e\u0440\u043c\u0430\u0442\u0435 dd.mm.yyyyy")
    private String date;
    /**
     * Массив значений для столбцов таблицы
     * (Required)
     * 
     */
    @JsonProperty("details")
    @JsonPropertyDescription("\u041c\u0430\u0441\u0441\u0438\u0432 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0439 \u0434\u043b\u044f \u0441\u0442\u043e\u043b\u0431\u0446\u043e\u0432 \u0442\u0430\u0431\u043b\u0438\u0446\u044b")
    private List<Detail> details = new ArrayList<Detail>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Идентификатор показателя. Совпадает с идентификатором показателя в main_info.schema
     * (Required)
     * 
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * Идентификатор показателя. Совпадает с идентификатором показателя в main_info.schema
     * (Required)
     * 
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Массив читабельных названий колонок для таблицы. Минимальное количество колонок - две, иначе таблица не получится
     * (Required)
     * 
     */
    @JsonProperty("titles")
    public List<String> getTitles() {
        return titles;
    }

    /**
     * Массив читабельных названий колонок для таблицы. Минимальное количество колонок - две, иначе таблица не получится
     * (Required)
     * 
     */
    @JsonProperty("titles")
    public void setTitles(List<String> titles) {
        this.titles = titles;
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
     * Массив значений для столбцов таблицы
     * (Required)
     * 
     */
    @JsonProperty("details")
    public List<Detail> getDetails() {
        return details;
    }

    /**
     * Массив значений для столбцов таблицы
     * (Required)
     * 
     */
    @JsonProperty("details")
    public void setDetails(List<Detail> details) {
        this.details = details;
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
        return new HashCodeBuilder().append(id).append(titles).append(date).append(details).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DetailInfo) == false) {
            return false;
        }
        DetailInfo rhs = ((DetailInfo) other);
        return new EqualsBuilder().append(id, rhs.id).append(titles, rhs.titles).append(date, rhs.date).append(details, rhs.details).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
