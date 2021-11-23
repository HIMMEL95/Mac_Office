package com.example.listview_test.data

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node

import org.w3c.dom.NodeList
import java.io.Serializable

import javax.xml.parsers.DocumentBuilderFactory

class MountainOpenApi {

    var mountainName : String? = null
    var mountainAddress : String? = null
    var mountainHeight : String? = null
    var mountainInfo : String? = null
    var mountainAdmin : String? = null

    private fun getTagValue(tag: String, eElement: Element): String? {
        val nlList: NodeList = eElement.getElementsByTagName(tag).item(0).getChildNodes()
        val nValue = nlList.item(0) as Node ?: return null
        return nValue.nodeValue
    }

    fun main(args: Array<String>) {

        val mountainData = MountainData()

        //Scanner sc = new Scanner(System.in);
        //String location = sc.nextLine();

        //String serviceKey = "=LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D";
        var page = 1
        try {
            while (true) {
                val urlAdd =
                    "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?serviceKey=LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D&searchWrd=%EB%B6%81%ED%95%9C%EC%82%B0"
                //String urlAdd = "http://apis.data.go.kr/1400000/service/cultureInfoService/mntInfoOpenAPI?serviceKey"
                //+ serviceKey + "&searchWrd=" + location;
                // String urlAdd =
                // "http://openapi.forest.go.kr/openapi/service/cultureInfoService/gdTrailInfoOpenAPI?serviceKey=LHSwFqTiNHfQEQw4PRhQhq4kY40LwyN9%2Fx4Q9NpOWQr8Dqqql2i630psVHxI4l7Ha37sT292A6M5SR1NCb41rQ%3D%3D&searchMtNm=%EA%B0%80&searchArNm=%EA%B0%95%EC%9B%90&pageNo=1&startPage=1&numOfRows=10&pageSize=10";
                val dbFactory = DocumentBuilderFactory.newInstance()
                val dBuilder = dbFactory.newDocumentBuilder()
                val doc: Document = dBuilder.parse(urlAdd)
                doc.documentElement.normalize()
                println("Root element : " + doc.documentElement.nodeName)
                val nList: NodeList = doc.getElementsByTagName("item")
                // System.out.println("파싱할 리스트 수 : " + nList.getLength());
                for (temp in 0 until nList.length) {
                    val nNode = nList.item(temp)
                    if (nNode.nodeType == Node.ELEMENT_NODE) {
                        val eElement: Element = nNode as Element
                        val mountainReason = getTagValue("mntidetails", eElement)
                        val mountainName = getTagValue("mntiname", eElement)
                        val mountainAddress = getTagValue("mntiadd", eElement)
                        val mountainHeight = getTagValue("mntihigh", eElement)
                        val mountainAdmin = getTagValue("mntiadmin", eElement)

                        mountainData.mountainName = mountainName
                        mountainData.mountainAddress = mountainAddress
                        mountainData.mountainHeight = mountainHeight
                        mountainData.mountainAdmin = mountainAdmin
                        mountainData.mountainInfo = mountainReason
                    }
                }
                page += 1
                // System.out.println("page number : " + page);
                if (page > 1) {
                    break
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //sc.close();
    }

}