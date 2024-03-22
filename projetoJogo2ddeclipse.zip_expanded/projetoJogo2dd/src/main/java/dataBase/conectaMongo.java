package dataBase;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import javax.swing.JOptionPane;
import org.bson.Document;

public class conectaMongo {
    public void getValues(){
        System.out.println("Método getValues()");
        //Conexão Mongo
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("pokemon");
        MongoCollection<Document> docs = db.getCollection("pokemon");
        for(Document doc: docs.find()){
            System.out.println("item: " + doc);
        }
        System.out.println("getValues() -  ok - Finalizou");
        
    }
    public void insertValue(String email, String senha){
        System.out.println("Método insertValues()");
        //Conexão Mongo
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("pokemon");
        MongoCollection<Document> docs = db.getCollection("pokemon");
        
        Entrada user = createUser(email, senha);
        //Cria um user obj da classe conectar, chamando o metodo createUser() - logo abaixo
        Document doc = createDocument(user);
        //Cria um doc que referencia o conteudo de user do createDocument(), obs: o Banco só entende objetos do tipo Document
        docs.insertOne(doc);//Insere no mongo o conteúdo de doc
        getValues();
        System.out.println("insertValues() - ok");
        
    }
    public Entrada createUser(String email, String senha){
        //Esse metodo deve ser uma entrada externa (interface, scanner ou JOptionPanel
        Entrada entrada = new Entrada();
        entrada.setEmail(email);
        entrada.setSenha(senha);
        
        return entrada;
    }
    
    public Document createDocument(Entrada user){
        Document docBuilder = new Document();
        
        docBuilder.append("email", user.getEmail());
        docBuilder.append("senha", user.getSenha());
        return docBuilder;
    }
    
  public boolean findValueslogin(String email, String senha) {
    boolean t = false;

    MongoClient mongo = new MongoClient("localhost", 27017);
    MongoDatabase db = mongo.getDatabase("pokemon");
    MongoCollection<Document> docs = db.getCollection("pokemon");

    // Verifique se existe um documento com o email e a senha fornecidos
    long count = docs.countDocuments(Filters.and(Filters.eq("email", email), Filters.eq("senha", senha)));

    // Se count for maior que 0, significa que as credenciais são válidas
    if (count > 0) {
        t = true;
    } else {
        JOptionPane.showMessageDialog(null, "Email ou senha não encontrados. Verifique se você já tem um cadastro.", "Erro", JOptionPane.OK_OPTION);
    }

    // Fecha a conexão com o MongoDB
    mongo.close();

    System.out.println("findValueslogin() - finalizou");
    return t;
}
    public void updateValues() {

        System.out.println("updateValues");
        //Entrada user = createUser();
        MongoClient mongo = new MongoClient("localhost", 27017);

        MongoDatabase db = mongo.getDatabase("pokemon");
        MongoCollection<Document> docs = db.getCollection("pokemon");

        docs.updateOne(Filters.eq("nome", "Kauan"), Updates.set("profissao", "Coordenador Ensino Superior"));
        System.out.println("Documento teve sucesso no update...");
        for (Document doc : docs.find()) {
            System.out.println("item update: " + doc);
        }

    }
    
    public  void deleteValues() {
        System.out.println("deleteValues");
        //Entrada user = createUser();
        MongoClient mongo = new MongoClient("localhost", 27017);

        MongoDatabase db = mongo.getDatabase("pokemon");
        MongoCollection<Document> docs = db.getCollection("pokemon");

        docs.deleteOne(Filters.eq("nome", "Marcio Pereira"));
        System.out.println("Documento teve sucesso no delete...");
        for (Document doc : docs.find()) {
            System.out.println("item update: " + doc);
        }

    }
}
