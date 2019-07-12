public class GetXMLTask extends AsyncTask<String,Void, Document>{

        @Override
        protected Document doInBackground(String... strings) {
            URL url;
            Document doc=null;
            try {
                url=new URL("http://tour.chungnam.go.kr/_prog/openapi/?func=tour&start=1&end=5");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();
            } catch (Exception e) {
                Toast.makeText(getActivity(),"에러입니다. 다시 한번 시도해주세요.",Toast.LENGTH_LONG).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document document) {
            String s1 = "";
            String s2 = "";
            String s3 = "";
            String s4 = "";
            NodeList nodeList = document.getElementsByTagName("item");
            for(int i=0;i<nodeList.getLength();i++){
                Node node = nodeList.item(i);
                Element fstElmnt = (Element) node;

                NodeList nm = fstElmnt.getElementsByTagName("nm");
                s1 = "idx = "+  nm.item(0).getChildNodes().item(0).getNodeValue() +"\n";

                NodeList loc = fstElmnt.getElementsByTagName("addr");
                s2 = "nm = "+  loc.item(0).getChildNodes().item(0).getNodeValue() +"\n";

                NodeList tel=fstElmnt.getElementsByTagName("tel");
                s3=tel.item(0).getChildNodes().item(0).getNodeValue()+"\n";

                NodeList list_img=fstElmnt.getElementsByTagName("list_img");
                s4=list_img.item(0).getChildNodes().item(0).getNodeValue()+"\n";

                adapter.addVO(ContextCompat.getDrawable(getContext(),img[0]),s1,s2,s3);
                listView.setAdapter(adapter);


            }
        }
    }