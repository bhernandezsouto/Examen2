/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twittersimulacion;

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
public class TwitterSimulacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("N7qhPXyWrndIpiW7fd8oqKTOl")
                    .setOAuthConsumerSecret("9mHGSEyqcbKaIs3qchFdYxNQ9oULuEl4rl4N8OjMXh4XMhYTV6")
                    .setOAuthAccessToken("3081212548-f6Vks5GUYtPIvLX2mNS64cvB4VmXdbNDKy8pE6V")
                    .setOAuthAccessTokenSecret("Ax7HvwYSwXj8Cl9zr8RPEdjuPCx7eRDl3yUn2XVypKVo7");

            Twitter twitter = new TwitterFactory(cb.build()).getInstance();
            PostTwitter(twitter);
            GetTimeLine(twitter);
            BuscarPorHash(twitter);
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterSimulacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
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
     *
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
     *
     * @param twitter
     * @throws TwitterException
     */
    public static void PostTwitter(Twitter twitter) throws TwitterException {
        String post = JOptionPane.showInputDialog(null, "Escribe lo que quieres postear a twitter");
        Status status = twitter.updateStatus(post);
        System.out.println("actualizado correctamente el estado a [" + status.getText() + "].");
    }

}
