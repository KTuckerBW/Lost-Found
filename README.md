# Software_Engineering_GroupProject
Push by Kait within tempBranch2 on 10/6/2026:
This is some more information about the classes I created to make it easier for everyone else to use them.

Post: Stores the data for one listing
csvPostGrabber: reads the csv file containing our data when you pass through the file path. Calls loadPosts() and returns an array of Posts
PostComponent: turns the data from one Post into a component which can be inserted into a panel, the panel can then be set to visible and display the Post

Use this to grab the post data:
csvPostGrabber GrabberNameHere = new csvPostGrabber("./data/LstAndFndListing.csv");

Use this to make an array of posts:
List<Post> ListNameHere = GrabberNameHere.loadPosts();

Use this to insert those posts into a panel:
for (Post post : ListNameHere) {
PanelNameHere.add(new PostComponent(post));
}

The GUI class which will use these methods needs these imports
import src.model.Post;
import src.postings.csvPostGrabber;
import src.ui.PostComponent;
import java.util.List;
