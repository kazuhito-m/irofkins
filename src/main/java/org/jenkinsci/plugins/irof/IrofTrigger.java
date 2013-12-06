package org.jenkinsci.plugins.irof;

import hudson.Extension;
import hudson.model.BuildableItem;
import hudson.model.Item;
import hudson.model.Cause;
import hudson.triggers.Trigger;
import hudson.triggers.TriggerDescriptor;

import org.kohsuke.stapler.DataBoundConstructor;

import twitter4j.Status;

public class IrofTrigger extends Trigger<BuildableItem> {

	private String twitterId;
	private String validRegexForTweet;
	
	@DataBoundConstructor
	public IrofTrigger(String twitterId , String validRegexForTweet) {
		this.twitterId = twitterId;
		this.validRegexForTweet = validRegexForTweet;
	}

	void run(Status status) {
		job.scheduleBuild(0, new IrofTriggerCause(status));
	}

	@Extension
	public static class DescriptorImpl extends TriggerDescriptor {

		public boolean isApplicable(Item item) {
			return item instanceof BuildableItem;
		}

		public String getDisplayName() {
			return "いろふ";
		}
	}

	public static class IrofTriggerCause extends Cause {

		Status status;

		public IrofTriggerCause(Status status) {
			this.status = status;
		}

		@Override
		public String getShortDescription() {
			return "Begin Jenkins Job by tweeter tweets.";
		}

		@Override
		public boolean equals(Object o) {
			if (o == null) {
				return false;
			}
			return this == o || this.toString().equals(o.toString());
		}

		@Override
		public int hashCode() {
			return 1623;
		}
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getValidRegexForTweet() {
		return validRegexForTweet;
	}

	public void setValidRegexForTweet(String validRegexForTweet) {
		this.validRegexForTweet = validRegexForTweet;
	}

}