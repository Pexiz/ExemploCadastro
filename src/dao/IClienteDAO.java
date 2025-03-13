package dao;

import domain.Cliente;

import java.util.Collection;

public interface IClienteDAO {

    public Boolean cadastrar(Cliente cliente);

    public Cliente excluir(Integer cpf);

    public void alterar (Cliente cliente);

    public Cliente consultar (Integer cpf);

    public Collection<Cliente> buscarTodos();
}
