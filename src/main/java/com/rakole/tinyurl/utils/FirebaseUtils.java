package com.rakole.tinyurl.utils;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirebaseUtils {

	public void deleteCollection(CollectionReference collection, int batchSize) {
		try {
			// retrieve a small batch of documents to avoid out-of-memory errors
			ApiFuture<QuerySnapshot> future = collection.limit(batchSize).get();
			int deleted = 0;
			// future.get() blocks on document retrieval
			List<QueryDocumentSnapshot> documents = future.get().getDocuments();
			for (QueryDocumentSnapshot document : documents) {
				document.getReference().delete();
				++deleted;
			}
			if (deleted >= batchSize) {
				// retrieve and delete another batch
				deleteCollection(collection, batchSize);
			}
		} catch (Exception e) {
			System.err.println("Error deleting collection : " + e.getMessage());
		}
	}

}
