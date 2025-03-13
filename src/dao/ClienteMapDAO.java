package dao;

import domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO {
    private static ClienteMapDAO instance;
    private final Map <Integer, Cliente> map;

    public ClienteMapDAO()
    {
        this.map = new HashMap<>();
    }

    public static ClienteMapDAO getInstance() {
        if (instance == null) {
            instance = new ClienteMapDAO();
        }
        return instance;
    }

        @Override
    public Boolean cadastrar(Cliente cliente) {
     if (this.map.containsKey(cliente.getCpf())) {
         return false;
     }
    this.map.put(cliente.getCpf(), cliente);
     return true;
    }

    @Override
    public Cliente excluir(Integer cpf) {
        Cliente clienteCadastrado = this.map.get(cpf);

        if (clienteCadastrado != null)
        {
            this.map.remove(cpf,clienteCadastrado);
        }

        return clienteCadastrado;
    }

    @Override
    public void alterar(Cliente cliente) {

        Cliente clienteCadastrado = this.map.get(cliente.getCpf());
        if (clienteCadastrado != null)
        {
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setEndereco(clienteCadastrado.getEndereco());
            clienteCadastrado.setEstado(cliente.getEstado());
            clienteCadastrado.setNumeroCasa(cliente.getNumeroCasa());
            clienteCadastrado.setTel(cliente.getTel());
        }

    }

    @Override
    public Cliente consultar(Integer cpf) {

        return map.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }
}

