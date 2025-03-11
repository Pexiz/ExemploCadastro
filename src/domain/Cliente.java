package domain;

import java.util.Objects;

public class Cliente {

    private String nome;
    private String endereco;
    private String cidade;
    private String estado;
    private Integer cpf;
    private int tel;
    private int numeroCasa;

    public Cliente(String nome, String endereco, Integer cpf, String estado, String cidade, int tel, int numeroCasa) {
        this.nome = nome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.estado = estado;
        this.cidade = cidade;
        this.tel = tel;
        this.numeroCasa = numeroCasa;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cpf == cliente.cpf;

    }


    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    @Override
    public String toString() {
        return "domain.Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf=" + cpf +
                '}';
    }
}
