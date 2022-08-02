import firebase from 'firebase/app';
import 'firebase/auth';
import 'firebase/database';

const firebaseConfig = {
	apiKey: "AIzaSyAjJhSaivCR8jfCqitmxJK1ZQ_lG2thuEw",
	authDomain: "finances-197dd.firebaseapp.com",
	projectId: "finances-197dd",
	storageBucket: "finances-197dd.appspot.com",
	messagingSenderId: "459199079751",
	appId: "1:459199079751:web:274bbc1cc2a2980777d862",
	measurementId: "G-TTGRC943R2"
};

if (!firebase.apps.length) {
	firebase.initializeApp(firebaseConfig);
}

export default firebase;




