package one.dio.projeto.service.impl;

import one.dio.projeto.model.Cliente;
import one.dio.projeto.model.ClienteRepository;
import one.dio.projeto.model.Endereco;
import one.dio.projeto.model.EnderecoRepository;
import one.dio.projeto.service.ClienteService;
import one.dio.projeto.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ViaCepService viaCepService;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarCliente(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBD = clienteRepository.findById(id);
        if(clienteBD.isPresent()){
            salvarCliente(cliente);
        }

    }
    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarCliente(Cliente cliente){
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet( () ->{

            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;

        });
        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);
    }
}
