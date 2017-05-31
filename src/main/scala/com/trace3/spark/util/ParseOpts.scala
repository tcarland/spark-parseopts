/**  A simple argument/option parser where command line flags with no argument 
 *   are defined as '-x' switches (boolean) and long options are defined as
 *   '--long-opt <val>' with a required argument. Any remaining arguments 
 *   not defined as a switch are returned as the remainer List.
 **/
package com.trace3.spark.util


import scala.collection.immutable.List
import scala.collection.immutable.Map


object ParseOpts {

  type OptMap  = Map[String, String]
  type OptList = List[String]
  

  def parseOpts ( args: OptList ) : (OptMap, OptList)  = 
  {
    def nextOpt ( argList: OptList, optMap: OptMap ) : (OptMap, OptList) = {
      val longOpt = "^--(\\S+)".r
      val regOpt  = "^-(\\S+)".r

      argList match {
        case Nil => (optMap, argList)
        case longOpt(opt)  :: value  :: tail => nextOpt(tail, optMap ++ Map(opt -> value))
        case regOpt(opt)             :: tail => nextOpt(tail, optMap ++ Map(opt -> null))
        case _ => (optMap, argList)
      }
    }
    
    nextOpt(args, Map())
  }

}

