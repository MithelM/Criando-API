package com.example.exercicioapi.Controller;


import com.example.exercicioapi.Model.Aluno;
import com.example.exercicioapi.Service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {


    @Autowired
    private AlunoService _as;

    @PostMapping
    public Aluno adicionar(@RequestBody Aluno aluno){
        Aluno aln = _as.cadastroAluno(aluno);
        return aln;
    }

    @GetMapping
    public List<Aluno> listar(){
        List<Aluno> alunos = _as.getAlunos();
        return alunos;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Aluno> obterAluno(@PathVariable(value = "id") Long id){
            Aluno aln = _as.getAlunoPorID(id);

        if (aln != null){
            return new ResponseEntity<Aluno>(aln, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@RequestBody Aluno aluno , @PathVariable( value = "id")Long id){
        Aluno aln = _as.getAlunoPorID(id);

        if (aln != null){
            if (aluno.getId() == null){
                aluno.setId(aln.getId());

                if (aluno.getCpf() == null){
                    aluno.setCpf(aln.getCpf());
                }if (aluno.getCurso() == null){
                    aluno.setCurso(aln.getCurso());
                }if (aluno.getNome() == null){
                    aluno.setNome(aln.getNome());
                }if (aluno.getRa() == 0){
                    aluno.setRa(aln.getRa());
                }
            }

            _as.cadastroAluno(aluno);
            return new ResponseEntity<>(aln, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Aluno> deletarAluno(@PathVariable( value = "id")Long id){
        Aluno aln = _as.getAlunoPorID(id);

        if (aln != null){
            _as.deletarAluno(aln);
            return new ResponseEntity<>(aln, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
