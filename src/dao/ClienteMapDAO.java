package dao;

import domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO implements IClienteDAO {
    private Map <Integer, Cliente> map;

    public ClienteMapDAO()
    {
        this.map = new HashMap<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
     if (this.map.containsKey(cliente.getCpf())) {
         return false;
     }
    this.map.put(cliente.getCpf(), cliente);
     return true;
    }

    // se fosse utilizando SET
    // return this.set.add(cliente) sem verificação pq SET nao deixa repetição de valor

    @Override
    public Cliente excluir(Integer cpf) {
        Cliente clienteCadastrado = this.map.get(cpf);

        if (clienteCadastrado != null)
        {
            this.map.remove(cpf,clienteCadastrado);
        }

        return clienteCadastrado;
    }

//    Cliente clienteEncontrado= null;
//    for (Cliente cliente : this.set)
//    {
//        if (cliente.getCpf().equals(cpf))
//        {
//            clienteencontrado = cliente;
//            break
//        }
//    }

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
    public Cliente consultar(int cpf) {

        return this.map.get(cpf);
    }


    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }
}

