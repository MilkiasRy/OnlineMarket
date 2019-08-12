package waa.edu.onlineshopping.dto;

import lombok.*;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportDto {
    private String name;
    private String description;
    private Collection<TableDto> table;

    public ReportDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<TableDto> getTable() {
        return table;
    }

    public void setTable(Collection<TableDto> table) {
        this.table = table;
    }
}
