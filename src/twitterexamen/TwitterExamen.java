/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterexamen;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author bhernandezsouto
 */
public class TwitterExamen {

    /**
     * Este es el metodo principal de la clase, en el se llama a los otros metodos de la aplicacion.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            

            Twitter twitter = new TwitterFactory().getInstance();
            PostTwitter(twitter);
            GetTimeLine(twitter);
            BuscarPorHash(twitter);
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Este metodo se encarga de realizar una busqueda de un String introducido por el usuario
     * @param twitter
     * @throws TwitterException
     */
    public static void BuscarPorHash(Twitter twitter) throws TwitterException {
        String post = JOptionPane.showInputDialog(null, "Escribe lo que quieres buscar en twitter");
        Query query = new Query(post);
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
    }

    /**
     * Este metodo muestra la linea del tiempo 
     * @param twitter
     * @throws TwitterException
     */
    public static void GetTimeLine(Twitter twitter) throws TwitterException {
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":"
                    + status.getText());
        }
    }

    /**
     * Este metodo postea un String a nuestro twitter
     * @param twitter
     * @throws TwitterException
     */
    public static void PostTwitter(Twitter twitter) throws TwitterException {
        String post = JOptionPane.showInputDialog(null, "Escribe lo que quieres postear a twitter");
        Status status = twitter.updateStatus(post);
        System.out.println("actualizado correctamente el estado a [" + status.getText() + "].");
    }

}
