// use the shell (made available under variable fsh)

println "Results:"
println "========"

new File('/tmp/results.txt').delete()
if (fsh.test(outputDir)) {
   fsh.get("${outputDir}/part-r-00000", '/tmp/results.txt')
}

def sout = new StringBuffer(), serr = new StringBuffer()
def proc = "sort -k2 -n /tmp/results.txt".execute()
proc.consumeProcessOutput(sout, serr)
proc.waitForOrKill(100000)
println "out> $sout"