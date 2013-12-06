package org.jenkinsci.plugins.irof;

import hudson.Plugin;
import hudson.model.TopLevelItem;
import hudson.model.AbstractProject;
import hudson.util.PluginServletFilter;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jenkins.model.Jenkins;
import org.jenkinsci.plugins.irof.ImageRequestFilter;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.StaplerRequest;

import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserStreamAdapter;
import twitter4j.auth.AccessToken;

public class IrofPlugin extends Plugin {

	private static final Logger LOGGER = Logger.getLogger(IrofPlugin.class
			.getName());

	public String consumerKey;
	public String consumerSecret;
	public String accessToken;
	public String accessTokenSecret;

	transient TwitterStreamFactory tsFactory;

	@Override
	public void start() {
		try {
			load();
			// Irofkinsの残骸
			PluginServletFilter.addFilter(new ImageRequestFilter());

			startStreaming();
			super.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		if (tsFactory != null) {
			if (tsFactory.getInstance() != null) {
				tsFactory.getInstance().shutdown();
			}
		}
		try {
			super.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void configure(StaplerRequest req, JSONObject formData) {
		consumerKey = formData.getString("consumerKey");
		consumerSecret = formData.getString("consumerSecret");
		accessToken = formData.getString("accessToken");
		accessTokenSecret = formData.getString("accessTokenSecret");
		try {
			save();
			super.configure(req, formData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		startStreaming();
	}

	public void startStreaming() {
		if (tsFactory != null) {
			if (tsFactory.getInstance() != null) {
				tsFactory.getInstance().shutdown();
			}
		}
		tsFactory = new TwitterStreamFactory();

		TwitterStream ts = tsFactory.getInstance();
		if (consumerKey != null && consumerSecret != null
				&& accessToken != null && accessTokenSecret != null) {
			ts.setOAuthConsumer(consumerKey, consumerSecret);
			ts.setOAuthAccessToken(new AccessToken(accessToken,
					accessTokenSecret));
			UserStreamAdapter usa = new UserStreamAdapter() {
				@Override
				public void onException(Exception e) {
					LOGGER.log(Level.SEVERE, e.getMessage(), e);
				}

				@SuppressWarnings({ "rawtypes", "unchecked" })
				@Override
				public void onStatus(Status status) {

					LOGGER.log(Level.INFO,
							status.getId() + ":" + status.getText(), status);

					if (!status.isRetweet()
							&& status.getUser().getId() == 14969725L) {

						List<TopLevelItem> items = Jenkins.getInstance()
								.getItems();
						for (TopLevelItem item : items) {
							if (item instanceof AbstractProject) {
								AbstractProject ab = (AbstractProject) item;
								if (ab.getTrigger(IrofTrigger.class) != null) {
									ab.getTrigger(IrofTrigger.class).run();
								}
							}
						}
					}
				}
			};
			ts.addListener(usa);
			ts.filter(new FilterQuery().follow(new long[] { 14969725L }));
		}
	}
}
