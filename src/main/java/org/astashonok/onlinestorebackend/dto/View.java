package org.astashonok.onlinestorebackend.dto;

import org.astashonok.onlinestorebackend.dto.abstracts.Entity;
import org.astashonok.onlinestorebackend.exceptions.basicexception.BackendLogicalException;
import org.astashonok.onlinestorebackend.exceptions.logicalexception.EmptyFieldException;
import org.astashonok.onlinestorebackend.exceptions.logicalexception.NullReferenceException;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import java.util.Objects;
import java.util.UUID;

public class View extends Entity {
    private String code;
    private Product product;

    @Transient
    private MultipartFile file;

    public View() {
        this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();
    }

    public View(Product product) {
        this();
        this.product = product;
    }

    public View(long id, Product product) {
        this(product);
        super.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws BackendLogicalException {
        if (code == null) {
            throw new NullReferenceException("The code must be indicated in the view! ");
        }
        if (code.isEmpty()) {
            throw new EmptyFieldException("The code must be filled in the view! ");
        }
        this.code = code;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) throws NullReferenceException {
        if (product == null) {
            throw new NullReferenceException("The code must be product in the view! ");
        }
        this.product = product;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "View{" +
                super.toString() +
                ", code='" + code + '\'' +
                ", product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        View view = (View) o;
        return Objects.equals(code, view.code) &&
                Objects.equals(product, view.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, product);
    }
}
