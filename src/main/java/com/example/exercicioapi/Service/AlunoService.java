package com.example.exercicioapi.Service;

import com.example.exercicioapi.Model.Aluno;
import com.example.exercicioapi.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository _ar;

    public Aluno cadastroAluno(Aluno aluno){
        _ar.save(aluno);
        return aluno;
    }

    public Aluno getAlunoPorID(Long id){
        Aluno aluno= _ar.findById(id).get();
        return aluno;
    }

    public List<Aluno> getAlunos(){
        return _ar.findAll();
    }

    public void deletarAluno(Aluno aluno){
        _ar.deleteById(aluno.getId());
    }
}
