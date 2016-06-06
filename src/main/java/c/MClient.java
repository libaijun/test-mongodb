package c;

import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MClient {
	public static void main(String[] args) {
		MongoClient client = new MongoClient("192.168.233.8", 27017);

		MongoDatabase database = client.getDatabase("mydb");

		System.out.println("Connect to database successfully");

		String collName = "test";
		// createCollection(database, "test");

		Document doc = new Document();
		doc.append("username", "wangwu");
		doc.append("age", 62);
		doc.append("salary", 1300);
		insertDocument(database, collName, doc);

		// findDocuments(database, collName);

		// updateDocument(database, collName, new Document("$set", new
		// Document("salary", 2100)));

		// deleteDocument(database, collName);
	}

	/**
	 * 创建集合
	 * 
	 * @param database
	 * @param collName
	 */
	public static void createCollection(MongoDatabase database, String collName) {
		database.createCollection(collName);
	}

	/**
	 * 批量插入数据
	 * 
	 * @param database
	 * @param collName
	 * @param list
	 */
	public static void insertDocument(MongoDatabase database, String collName, List<Document> list) {
		MongoCollection<Document> collection = database.getCollection(collName);
		collection.insertMany(list);
	}

	/**
	 * 插入一条数据
	 * 
	 * @param database
	 * @param collName
	 * @param doc
	 */
	public static void insertDocument(MongoDatabase database, String collName, Document doc) {
		MongoCollection<Document> collection = database.getCollection(collName);
		collection.insertOne(doc);
	}

	/**
	 * 查询全部数据
	 * 
	 * @param database
	 * @param collName
	 */
	public static void findDocuments(MongoDatabase database, String collName) {
		MongoCollection<Document> collection = database.getCollection(collName);
		FindIterable<Document> docs = collection.find();
		MongoCursor<Document> iterator = docs.iterator();
		while (iterator.hasNext()) {
			Document doc = iterator.next();
			System.out.println(doc.toJson());
		}
	}

	/**
	 * 更新一条语句
	 * 
	 * @param database
	 * @param collName
	 */
	public static void updateDocument(MongoDatabase database, String collName, Document update) {
		MongoCollection<Document> collection = database.getCollection(collName);
		collection.updateOne(Filters.eq("age", 34), update);
	}

	/**
	 * 删除一条语句
	 * 
	 * @param database
	 * @param collName
	 */
	public static void deleteDocument(MongoDatabase database, String collName) {
		MongoCollection<Document> collection = database.getCollection(collName);
		collection.deleteOne(Filters.eq("age", 34));
	}

}
