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

	@DataBoundConstructor
	public IrofTrigger() {
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
			return "Started by irof";
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

}