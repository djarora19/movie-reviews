package adj.learn.springboot.ratingdata.model;

import java.util.List;

public class UserRating
{
	private String userName;
	private List<Rating> ratings;

	public UserRating()
	{
	}

	public UserRating(String userName, List<Rating> ratings)
	{
		this.userName = userName;
		this.ratings = ratings;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public List<Rating> getRatings()
	{
		return ratings;
	}

	public void setRatings(List<Rating> ratings)
	{
		this.ratings = ratings;
	}

}