package Bean;
 
import Component.Document;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
 
@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentService implements Serializable{
     
    public TreeNode createDocuments() {
        TreeNode root = new DefaultTreeNode(new Document("Files", "-", "Folder"), null);
         
        TreeNode documents = new DefaultTreeNode(new Document("سامسونج", "-", "Folder"), root);
        TreeNode pictures = new DefaultTreeNode(new Document("اوبو", "-", "Folder"), root);
        TreeNode movies = new DefaultTreeNode(new Document("ايفون", "-", "Folder"), root);
         
        TreeNode work = new DefaultTreeNode(new Document("نوت", "-", "Folder"), documents);
        TreeNode primefaces = new DefaultTreeNode(new Document("جلاكسي", "-", "Folder"), documents);
         
        //Documents
        TreeNode expenses = new DefaultTreeNode("document", new Document("نوت3", "30 KB", "Word Document"), work);
        TreeNode resume = new DefaultTreeNode("document", new Document("نوت4", "10 KB", "Word Document"), work);
        TreeNode refdoc = new DefaultTreeNode("document", new Document("نوت5", "40 KB", "Pages Document"), primefaces);
         
        //Pictures
        TreeNode barca = new DefaultTreeNode("picture", new Document("barcelona.jpg", "30 KB", "JPEG Image"), pictures);
        TreeNode primelogo = new DefaultTreeNode("picture", new Document("logo.jpg", "45 KB", "JPEG Image"), pictures);
        TreeNode optimus = new DefaultTreeNode("picture", new Document("optimusprime.png", "96 KB", "PNG Image"), pictures);
         
        //Movies
        TreeNode pacino = new DefaultTreeNode(new Document("Al Pacino", "-", "Folder"), movies);
        TreeNode deniro = new DefaultTreeNode(new Document("Robert De Niro", "-", "Folder"), movies);
         
        TreeNode scarface = new DefaultTreeNode("mp3", new Document("Scarface", "15 GB", "Movie File"), pacino);
        TreeNode carlitosWay = new DefaultTreeNode("mp3", new Document("Carlitos' Way", "24 GB", "Movie File"), pacino);
         
        TreeNode goodfellas = new DefaultTreeNode("mp3", new Document("Goodfellas", "23 GB", "Movie File"), deniro);
        TreeNode untouchables = new DefaultTreeNode("mp3", new Document("Untouchables", "17 GB", "Movie File"), deniro);
         
        return root;
    }
}