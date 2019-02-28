package common.Repository;

import ValidatorPackage.NotaValidator;
import common.Domain.Nota;
import common.Domain.Student;
import common.Domain.Tema;

public class NoteRepository extends AbstractFileRepository<String,Nota> {
    private NotaValidator val = new NotaValidator();
    private String fileName;

    public NoteRepository(NotaValidator val,String fileName) {
        super(fileName, val);
        this.val = val;
        this.fileName = fileName;
        super.loadFromFile();
    }

    @Override
    public Nota extractEntity(String entity) {

        String[] msg = entity.split(" ");
        Tema t = new Tema(Integer.parseInt(msg[0]),msg[1],Integer.parseInt(msg[2]),Integer.parseInt(msg[3]));

        Student s = new Student(Integer.parseInt(msg[4]),msg[5],Integer.parseInt(msg[6]),msg[7],msg[8]);

        Nota n = new Nota(t, s, Float.parseFloat(msg[9]),Integer.parseInt(msg[10]));

        return n;
    }
}
